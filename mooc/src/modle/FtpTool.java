package modle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpTool {
	public FTPClient ftp;
	public boolean connect(String path,String addr,int port) throws Exception {
		boolean result = false;
		ftp = new FTPClient();
		int reply;
		ftp.connect(addr,port);
		ftp.login("iter","!Q1234567890");
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return result;
		}
		ftp.changeWorkingDirectory(path);
		result = true;
		return result;
	}
	
	public boolean downFile(String remotePath,String fileName,String localPath) {  
		   boolean success = false;    
		   try {  
		       int reply;
		       reply = ftp.getReplyCode();  
		       if (!FTPReply.isPositiveCompletion(reply)) {  
		           ftp.disconnect();  
		           return success;  
		       }  
		       ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录  
		       FTPFile[] fs = ftp.listFiles();  
		       for(FTPFile ff:fs){  
		           if(ff.getName().equals(fileName)){  
		               File localFile = new File(localPath+"/"+ff.getName());  
		                 
		               OutputStream is = new FileOutputStream(localFile);   
		               ftp.retrieveFile(ff.getName(), is);  
		               is.close();  
		           }  
		       }  
		         
		       ftp.logout();  
		       success = true;  
		   } catch (IOException e) {  
		       e.printStackTrace();  
		   } finally {  
		       if (ftp.isConnected()) {  
		           try {  
		               ftp.disconnect();  
		           } catch (IOException ioe) {  
		           }  
		       }  
		   }  
		   return success;  
	}
	/** 
	 * 
	 * @param file 上传的文件或文件夹 
	 * @throws Exception 
	 */
	public void upload(File file) throws Exception{
		if(file.isDirectory()){
			ftp.makeDirectory(file.getName());
			ftp.changeWorkingDirectory(file.getName());
			String[] files = file.list();
			for (int i = 0;i < files.length;i++) {
				File file1 = new File(file.getPath()+"\\"+files[i] );
				if(file1.isDirectory()){
					upload(file1);
					ftp.changeToParentDirectory();
				}else{
					File file2 = new File(file.getPath()+"\\"+files[i]);
					FileInputStream input = new FileInputStream(file2);
					if(ftp.storeFile(file2.getName(), input))System.out.println("ok");;
					input.close();
				}
			}
		}else{
			File file2 = new File(file.getPath());
			FileInputStream input = new FileInputStream(file2);
			System.out.println(file2.getName());
			if(ftp.storeFile(file2.getName(), input))System.out.println("完成了传输");;
			input.close();
		}
	}
	
}
