package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * 文字列のユーティリティ。
 */
public class StringUtility extends Object
{
	/**
	 * 文字列(aString)をCSV文字列にして応答するクラスメソッド。
	 * スペースやカンマなどの特殊文字が含まれる場合にはダブルクォートで囲まれる。
	 * @param aString 文字列
	 * @return CSV文字列
	 */
	@Test
	@DisplayName("暫定")
	public static String csvString(String aString)
	{
		StringBuffer aBuffer = new StringBuffer();
		aBuffer.append('"');
		aBuffer.append(',');
		aBuffer.append(' ');
		aBuffer.append('\t');
		aBuffer.append('\r');
		aBuffer.append('\n');
		aBuffer.append('\f');
		String specialCharacters = aBuffer.toString();

		boolean needDoubleQuote = false;
		for (int index = 0; index < aString.length(); index++)
		{
			Character aCharacter = aString.charAt(index);
			if (specialCharacters.indexOf(aCharacter) != -1) { needDoubleQuote = true; }
		}
		String theString = aString;
		if (needDoubleQuote)
		{
			aBuffer = new StringBuffer();
			aBuffer.append('"');
			for (int index = 0; index < aString.length(); index++)
			{
				Character aCharacter = aString.charAt(index);
				aBuffer.append(aCharacter);
				if (aCharacter == '"') { aBuffer.append('"'); }
			}
			aBuffer.append('"');
			theString = aBuffer.toString();
		}

		assertNotNull(aString);
		assertNotNull(aBuffer);

		return theString;
	}

	/**
	 * 入出力する際の文字コードを応答するクラスメソッド。
	 * @return 文字コード
	 */
	@Test
	@DisplayName("暫定")
	public static String encodingSymbol()
	{
		return "UTF-8";
	}

	/**
	 * バッファードリーダー(aBufferdReader)から一文字を読み込んで応答するクラスメソッド。
	 * @param aBufferdReader バッファードリーダー
	 * @return 一文字
	 */
	@Test
	@DisplayName("暫定")
	public static Character getChar(BufferedReader aBufferdReader)
	{
		try
		{
			int charValue = aBufferdReader.read();
			if (charValue == -1) { return null; }
			Character aCharacter = Character.valueOf((char)charValue);
			if (aCharacter == '\n') // LF
			{
				return Character.valueOf('\n');
			}
			else
			{
				if (aCharacter == '\r') // CR
				{
					aBufferdReader.mark(256);
					charValue = aBufferdReader.read();
					if (charValue == -1) // EOF
					{
						aBufferdReader.reset();
						return Character.valueOf('\n');
					}
					aCharacter = Character.valueOf((char)charValue);
					if (aCharacter == '\n') // CRLF
					{
						return aCharacter;
					}
					else
					{
						aBufferdReader.reset();
						return Character.valueOf('\n');
					}
				}
				else
				{
					return aCharacter;
				}
			}
		}
		catch (IOException anException) { anException.printStackTrace(); }

				
		assertNotNull(aBufferdReader);
		return null;

	}

	/**
	 * バッファードリーダー(aBufferdReader)からCSVとして一行を読み込んで集まりにして応答するクラスメソッド。
	 * @param aBufferdReader バッファードリーダー
	 * @return 文字列の集まり：CSVの一行
	 */
	@Test
	@DisplayName("暫定")
	public static List<String> getRowCSV(BufferedReader aBufferdReader)
	{
		List<String> aCollection = new ArrayList<String>();
		StringBuffer aBuffer = new StringBuffer();
		boolean aBoolean = true;
		Character aCharacter = null;
		while (aBoolean)
		{
			aCharacter = StringUtility.getChar(aBufferdReader);
			if (aCharacter == null)
			{
				if (aBuffer.length() == 0) { return null; } else { break; }
			}
			if (aCharacter == '\n')
			{
				aBoolean = false;
			}
			else
			{
				if (aCharacter == ',')
				{
					aCollection.add(aBuffer.toString());
					aBuffer = new StringBuffer();
				}
				else
				{
					if (aCharacter == '"')
					{
						boolean aLoop = true;
						while (aLoop)
						{
							aCharacter = StringUtility.getChar(aBufferdReader);
							if (aCharacter == null)
							{
								if (aBuffer.length() == 0) { return null; } else { break; }
							}
							if (aCharacter == '"')
							{
								try
								{
									aBufferdReader.mark(256);
									aCharacter = StringUtility.getChar(aBufferdReader);
									if (aCharacter == null)
									{
										if (aBuffer.length() == 0) { return null; } else { break; }
									}
									if (aCharacter == '"')
									{
										aBuffer.append('"');
									}
									else
									{
										aBufferdReader.reset();
										aLoop = false;
									}
								}
								catch (IOException anException) { anException.printStackTrace(); }
							}
							else
							{
								aBuffer.append(aCharacter);
							}
						}
					}
					else
					{
						aBuffer.append(aCharacter);
					}
				}
			}
		}
		aCollection.add(aBuffer.toString());

		assertNotNull(aCollection);
		assertNotNull(aBuffer);
		assertNotNull(aCharacter);
		return aCollection;

	}

	/**
	 * 文字列の集まり(aCollection)をCSVの一行としてバッファードライター(aBufferedWriter)へ書き込むクラスメソッド。
	 * @param aBufferedWriter バッファードライター
	 * @param aCollection 文字列の集まり：CSVの一行
	 */
	@Test
	@DisplayName("暫定")
	public static void putRowCSV(BufferedWriter aBufferedWriter, List<String> aCollection)
	{
		try
		{
			for (int index = 0; index < aCollection.size(); index++)
			{
				if (index > 0) { aBufferedWriter.write(","); }
				String aString = aCollection.get(index);
				aBufferedWriter.write(StringUtility.csvString(aString));
			}
			aBufferedWriter.write('\n');
		}
		catch (IOException anException) { anException.printStackTrace(); }


		assertNotNull(aBufferedWriter);
		return;

	}

	/**
	 * 指定されたファイルからレコード(row)を読み込んで、それをレコードリストにして応答するクラスメソッド。
	 * @param aFile ファイル
	 * @return レコードリスト
	 */
	@Test
	@DisplayName("暫定")
	public static List<List<String>> readRowsFromFile(File aFile)
	{
		List<List<String>> aCollection = new ArrayList<List<String>>();
		try
		{
			FileInputStream inputStream = new FileInputStream(aFile);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StringUtility.encodingSymbol());
			BufferedReader inputReader = new BufferedReader(inputStreamReader);

			List<String> aRow = null;
			while ((aRow = StringUtility.getRowCSV(inputReader)) != null)
			{
				aCollection.add(aRow);
			}

			inputReader.close();
		}
		catch (FileNotFoundException anException) { anException.printStackTrace(); }
		catch (UnsupportedEncodingException anException) { anException.printStackTrace(); }
		catch (IOException anException) { anException.printStackTrace(); }

		assertNotNull(aCollection);
		return aCollection;

	}

	/**
	 * 指定されたファイル文字列からレコード(row)を読み込んで、それをレコードリストにして応答するクラスメソッド。
	 * @param fileString ファイル名
	 * @return レコードリスト
	 */
	@Test
	@DisplayName("暫定")
	public static List<List<String>> readRowsFromFile(String fileString)
	{
		File aFile = new File(fileString);

		List<List<String>> aCollection = StringUtility.readRowsFromFile(aFile);

		assertNotNull(aFile);
		assertNotNull(aCollection);
		return aCollection;

	}

	/**
	 * 指定されたファイルからテキストを読み込んで、それを行リストにして応答するクラスメソッド。
	 * @param aFile ファイル
	 * @return 行リスト
	 */
	@Test
	@DisplayName("暫定")
	public static List<String> readTextFromFile(File aFile)
	{
		List<String> aCollection = new ArrayList<String>();

		try
		{
			FileInputStream inputStream = new FileInputStream(aFile);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StringUtility.encodingSymbol());
			BufferedReader inputReader = new BufferedReader(inputStreamReader);

			String aString = null;
			while ((aString = inputReader.readLine()) != null)
			{
				aCollection.add(aString);
			}

			inputReader.close();
		}
		catch (FileNotFoundException anException) { anException.printStackTrace(); }
		catch (UnsupportedEncodingException anException) { anException.printStackTrace(); }
		catch (IOException anException) { anException.printStackTrace(); }

		assertNotNull(aCollection);
		return aCollection;

	}

	/**
	 * 指定されたファイル文字列からテキストを読み込んで、それを行リストにして応答するクラスメソッド。
	 * @param fileString ファイル名
	 * @return 行リスト
	 */
	@Test
	@DisplayName("暫定")
	public static List<String> readTextFromFile(String fileString)
	{
		File aFile = new File(fileString);

		List<String> aCollection = StringUtility.readTextFromFile(aFile);

		assertNotNull(aFile);
		assertNotNull(aCollection);
		return aCollection;

	}

	/**
	 * 指定されたURL文字列からテキストを読み込んで、それを行リストにして応答するクラスメソッド。
	 * @param urlString テキストのためのURL文字列
	 * @return 行リスト
	 */
	@Test
	@DisplayName("暫定")
	public static List<String> readTextFromURL(String urlString)
	{
		URL aURL = null;
		try { aURL = new URL(urlString); }
		catch (MalformedURLException anException) { anException.printStackTrace(); }

		List<String> aCollection = StringUtility.readTextFromURL(aURL);

		assertNotNull(aURL);
		return aCollection;

	}

	/**
	 * 指定されたURLからテキストを読み込んで、それを行リストにして応答するクラスメソッド。
	 * @param aURL テキストのためのURL
	 * @return 行リスト
	 */
	@Test
	@DisplayName("暫定")
	public static List<String> readTextFromURL(URL aURL)
	{
		List<String> aCollection = new ArrayList<String>();

		try
		{
			InputStream inputStream = aURL.openStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StringUtility.encodingSymbol());
			BufferedReader inputReader = new BufferedReader(inputStreamReader);

			String aString = null;
			while ((aString = inputReader.readLine()) != null)
			{
				aCollection.add(aString);
			}

			inputReader.close();
		}
		catch (UnsupportedEncodingException anException) { anException.printStackTrace(); }
		catch (IOException anException) { anException.printStackTrace(); }

		assertNotNull(aCollection);
		return aCollection;

	}

	/**
	 * 文字列をセパレータで分割したトークン列を応答するクラスメソッド。
	 * @param string 文字列
	 * @param separators 分割文字列
	 * @return セパレータで分割したトークン列
	 */
	@Test
	@DisplayName("暫定")
	public static List<String> splitString(String string, String separators)
	{
		List<Integer> indexes;
		int stop;
		int index;
		List<String> result;

		indexes = new ArrayList<Integer>();
		indexes.add(-1);
		stop = string.length();
		for (index = 0; index < stop; index++)
		{
			if ((separators.indexOf(string.charAt(index))) >= 0)
			{
				indexes.add(index);
			}
		}
		indexes.add(stop);
		stop = indexes.size() - 1;
		result = new ArrayList<String>();
		for (index = 0; index < stop; index++)
		{
			int start;
			int end;

			start = indexes.get(index) + 1;
			end = indexes.get(index + 1) - 1;
			if (end >= start)
			{
				result.add(string.substring(start, end + 1));
			}
		}

		assertNotNull(result);
		return result;

	}

	/**
	 * 指定されたレコードリストを、指定されたファイルに書き出すクラスメソッド。
	 * @param aCollection レコードリスト
	 * @param aFile ファイル
	 */
	@Test
	@DisplayName("暫定")
	public static void writeRows(List<List<String>> aCollection, File aFile)
	{
		try
		{
			FileOutputStream outputStream = new FileOutputStream(aFile);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StringUtility.encodingSymbol());
			BufferedWriter outputWriter = new BufferedWriter(outputStreamWriter);

			for (List<String> aRow : aCollection)
			{
				StringUtility.putRowCSV(outputWriter, aRow);
			}

			outputWriter.close();
		}
		catch (FileNotFoundException anException) { anException.printStackTrace(); }
		catch (UnsupportedEncodingException anException) { anException.printStackTrace(); }
		catch (IOException anException) { anException.printStackTrace(); }

		assertNotNull(aCollection);
		return;

	}

	/**
	 * 指定されたレコードリストを、指定されたファイル名のファイルに書き出すクラスメソッド。
	 * @param aCollection レコードリスト
	 * @param fileString ファイル名
	 */
	@Test
	@DisplayName("暫定")
	public static void writeRows(List<List<String>> aCollection, String fileString)
	{
		File aFile = new File(fileString);
		StringUtility.writeRows(aCollection, aFile);

		assertNotNull(aFile);
		return;

	}

	/**
	 * 指定された行リストを、指定されたファイルに書き出すクラスメソッド。
	 * @param aCollection 行リスト
	 * @param aFile ファイル
	 */
	@Test
	@DisplayName("暫定")
	public static void writeText(List<String> aCollection, File aFile)
	{
		try
		{
			FileOutputStream outputStream = new FileOutputStream(aFile);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StringUtility.encodingSymbol());
			BufferedWriter outputWriter = new BufferedWriter(outputStreamWriter);

			for (String aString : aCollection)
			{
				outputWriter.write(aString + "\n");
			}

			outputWriter.close();
		}
		catch (IOException anException) { anException.printStackTrace(); }

		assertNotNull(aFile);
		return;

	}

	/**
	 * 指定された行リストを、指定されたファイル名のファイルに書き出すクラスメソッド。
	 * @param aCollection 行リスト
	 * @param fileString ファイル名
	 */
	@Test
	@DisplayName("暫定")
	public static void writeText(List<String> aCollection, String fileString)
	{
		File aFile = new File(fileString);
		StringUtility.writeText(aCollection, aFile);

		assertNotNull(aFile);
		return;

	}
}
