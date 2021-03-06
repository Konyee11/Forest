package utility;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


/**
 * ファイルのユーティリティ。
 */
public class FileUtility extends Object
{
	/**
	 * カレントディレクトリを文字列（最後が必ずパス区切り文字となる）として応答する。
	 * @return カレントディレクトリの文字列
	 */

	public static String currentDirectory()
	{
		String aString = System.getProperty("user.dir");
		if (aString == null)
		{
			aString = new File(".").getAbsoluteFile().getParent();
		}
		if (aString == null)
		{
			aString = ".";
		}
		StringBuffer aBuffer = new StringBuffer();
		aBuffer.append(aString);
		if (aString.charAt(aString.length() - 1) != File.separatorChar)
		{
			aBuffer.append(File.separator);
		}
		aString = aBuffer.toString();


		return aString;

	}
	/**
	 * 開こうとするファイルを受け取り、そのファイルに関連付けられたアプリケーションを起動してファイルを開く。
	 * @param aFile 開こうとするファイル
	 */

	public static void open(File aFile)
	{
		Desktop aDesktop = Desktop.getDesktop();
		try { aDesktop.open(aFile); }
		catch (IOException anException) { anException.printStackTrace(); }


		return;

	}

	/**
	 * 開こうとするファイル名を文字列として受け取り、そのファイルに関連付けられたアプリケーションを起動してファイルを開く。
	 * @param aString 開こうとするファイル名の文字列
	 */

	public static void open(String aString)
	{
		File aFile = new File(aString);
		FileUtility.open(aFile);

		return;

	}
}
