package Phaser_basic;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable
{
	private final String initPath;
	private final String fileExtension;
	// store file absolute path we find
	private List<String> results;
	// phaser
	private Phaser phaser;
	
	
	
	public FileSearch(String initPath, String fileExtension, Phaser phaser)
	{
		super();
		this.initPath = initPath;
		this.fileExtension = fileExtension;
		this.phaser = phaser;
		this.results = new ArrayList<>();
	}

	// auxiliary method
	// 遍历所有文件找目标
	private void dirProcess(File file)
	{
		File[] list = file.listFiles();
		if (null != list)
		{
			for (File subFile : list)
			{
				if (subFile.isDirectory())
				{
					dirProcess(subFile);
				}
				else
				{
					fileProcess(subFile);
				}
			}
		}
	}
	
	private void fileProcess(File file)
	{
		if (file.getName().endsWith(fileExtension))
		{
			results.add(file.getAbsolutePath());
		}
	}
	
	// filter files
	private void filterResult()
	{
		List<String> tmp = new ArrayList<>();
		// current date
		long current = new Date().getTime();
		
		for (String path : results)
		{
			File file = new File(path);
			long fileDate = file.lastModified();
			// 900 days 之内修改的文件都合格
			if (current - fileDate < TimeUnit.MILLISECONDS.convert(900, TimeUnit.DAYS))
			{
				tmp.add(path);
			}
		}
		results = tmp;
	}
	
	private boolean checkResults()
	{
		if (results.isEmpty())
		{
			System.out.printf("%s: phase %d: 0 results\n",
							 Thread.currentThread().getName(),
							 phaser.getPhase());
			System.out.printf("%s: phase %d: end\n",
							 Thread.currentThread().getName(),
							 phaser.getPhase());
			// 达到 并 注销
			phaser.arriveAndDeregister();
			return false;
		}

		System.out.printf("%s: phase %d: %d results\n",
						  Thread.currentThread().getName(),
						  phaser.getPhase(),
						  results.size());
		// 到达 并 等待
		phaser.arriveAndAwaitAdvance();
		return true;
	}
	
	private void showInfo()
	{
		for (String path : results)
		{
			System.out.printf("%s: %s\n",
					         Thread.currentThread().getName(),
					         new File(path).getAbsolutePath());
		}
		phaser.arriveAndAwaitAdvance();
	}
	
	@Override
	public void run()
	{
		// wait for all threads are created
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("%s: starting\n", 
						  Thread.currentThread().getName());
		
		File file = new File(initPath);
		// 检查输入是不是文件夹
		if (file.isDirectory())
		{
			dirProcess(file);
		}
		// 结果为空?
		if (!checkResults())
		{
			return;
		}
		
		filterResult();
		// 结果为空?
		if (!checkResults())
		{
			return;
		}
		
		showInfo();
		phaser.arriveAndDeregister();
		System.out.printf("%s: work completed\n",
						 Thread.currentThread().getName());
	}

}
