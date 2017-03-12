package com.muletut.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muletut.dao.DaoService;
import com.muletut.exceptions.MuletutException;
import com.muletut.search.Indexer;
import com.muletut.search.LuceneConstants;
import com.muletut.search.Searcher;
import com.muletut.search.TextFileFilter;

@Service("muletutService")
public class MuletutServiceImpl implements MuletutService {
	@Autowired
	DaoService daoService;

	Indexer indexer;
	Searcher searcher;
	QueryParser queryParser;

	/************* Add Index Menu Items ***************/
	@Override
	public boolean addMenuItems() throws MuletutException {
		String[] menuItemsNames = new String[] { "overview", "environment setup",
				"building your first mule application", "flows", "HTTP", "file", "database", "variable", "java",
				"entry point resolver", "invoke", "dataweave-getting started", "dataweave-operators",
				"dataweave-selectors", "filters", "catch exception strategy", "reference exception strategy",
				"choice exception strategy", "object store", "batch processing", "java virtual machine(VM)", "JMS",
				"for each", "choice", "poll", "scatter gather", "collection splitter", "collection aggregator",
				"composite source", "property", "salesforce", "web service consumer", "SFTP", "async" };
		return daoService.addMenuItems(menuItemsNames);
	}

	/************* Get Index Menu Items ***************/
	@Override
	public ArrayList<String> getIndexMenu() throws MuletutException {
		ArrayList<String> menuItems = daoService.getIndexMenu();
		return menuItems;
	}

	/******************** Read tut *****************/
	@Override
	public String readFile(String title) throws MuletutException {
		String type = "tuts";
		return reader(type, title);
	}

	/************* Add Reference Menu Items ***************/
	@Override
	public boolean addReferenceMenuItems() throws MuletutException {
		String[] referenceMenuItemsNames = new String[] { "flows", "HTTP", "file", "database", "variable", "java",
				"invoke", "filters", "catch exception strategy", "reference exception strategy",
				"choice exception strategy", "object store", "batch processing", "java virtual machine(VM)", "JMS",
				"for each", "choice", "scatter gather", "collection splitter", "collection aggregator",
				"composite source", "property", "salesforce", "web service consumer", "async", "SFTP" };
		return daoService.addReferenceMenuItems(referenceMenuItemsNames);
	}

	/************* Get Reference Menu Items ***************/
	@Override
	public ArrayList<String> getReferenceMenu() throws MuletutException {
		ArrayList<String> referenceMenuItems = daoService.getReferenceMenu();
		return referenceMenuItems;
	}

	/************* Add Blog Posts ***************/
	@Override
	public boolean addBlogPosts() throws MuletutException {
		String[] blogPostsNames = new String[] { "10+ Angular 2 and WordPress Integrations Examples", "flows", "HTTP",
				"file", "database", "variable", "java", "invoke", "filters", "catch exception strategy",
				"reference exception strategy", "choice exception strategy", "object store", "batch processing",
				"java virtual machine(VM)", "JMS", "for each", "choice", "scatter gather", "collection splitter",
				"collection aggregator", "composite source", "property", "salesforce", "web service consumer", "async",
				"SFTP" };
		return daoService.addBlogPosts(blogPostsNames);
	}

	/************* Get Blog Posts ***************/
	@Override
	public ArrayList<String> getBlogPosts() throws MuletutException {
		ArrayList<String> blogPosts = daoService.getBlogPosts();
		return blogPosts;
	}

	/************* Get Blog Posts for single page ***************/
	@Override
	public ArrayList<String> getBlogPostsForSingle(String postName) throws MuletutException {
		ArrayList<String> blogPosts = daoService.getBlogPostsForSingle(postName);
		return blogPosts;
	}

	/******************** Read post *****************/
	@Override
	public String readPost(String title) throws MuletutException {
		String type = "posts";
		return reader(type, title);
	}

	/**
	 * Method to read file
	 * 
	 * @param type
	 * @param title
	 * @return
	 * @throws MuletutException
	 */
	private String reader(String type, String title) throws MuletutException {
		StringBuilder contentBuilder = null;
		BufferedReader br = null;
		try {
			contentBuilder = new StringBuilder();
			br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("data/" + type + "/" + title + ".html")));
			String str;
			boolean flag = false;
			while ((str = br.readLine()) != null) {
				contentBuilder.append(str);
				if (flag) {
					contentBuilder.append(System.getProperty("line.separator"));
				}
				if (!flag) {
					flag = str.contains("<pre>");
				}
				if (str.contains("</pre>"))
					flag = false;
			}
			br.close();
		} catch (IOException e) {
			throw new MuletutException("Can't read file");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				throw new MuletutException("Can't read file");
			}
		}
		String content = contentBuilder.toString();
		File file = new File("F:/data1.html");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			@SuppressWarnings("resource")
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	@Override
	public void createIndex() throws MuletutException {
		try {
			String indexDir = getIndexDir();
			String dataDir = getDataDir();
			indexer = new Indexer(indexDir);
			indexer.createIndex(dataDir, new TextFileFilter());
		} catch (IOException e) {
			throw new MuletutException("Can't find file to index");
		} finally {
			try {
				indexer.close();
			} catch (IOException e) {
				throw new MuletutException("Problem while indexing");
			}
		}
	}

	private String getIndexDir() {
		ClassLoader classLoader = getClass().getClassLoader();
		String path = new File(classLoader.getResource("indexes/index.index").getFile()).getParent();
		return path + "/tutIndex";
	}

	private String getDataDir() {
		ClassLoader classLoader = getClass().getClassLoader();
		String path = new File(classLoader.getResource("data/data.data").getFile()).getParent();
		return path + "/tuts";
	}

	@Override
	public ArrayList<String> search(String searchString) throws MuletutException {
		try {
			String indexDir = getIndexDir();
			searcher = new Searcher(indexDir);
			queryParser = new QueryParser(Version.LUCENE_36, LuceneConstants.CONTENTS,
					new StandardAnalyzer(Version.LUCENE_36));
			Query query = queryParser.parse(searchString.replaceAll(" ", "") + "*");
			TopDocs hits = searcher.search(query);
			System.out.println(hits.totalHits + " documents found");
			ArrayList<String> matchedTitles = new ArrayList<String>();
			
			for (ScoreDoc scoreDoc : hits.scoreDocs) {
				Document doc = searcher.getDocument(scoreDoc);
				String path = doc.get(LuceneConstants.FILE_PATH);
				matchedTitles.add(path.substring(path.lastIndexOf("\\")+1).split("\\.")[0].trim());
			}
			return matchedTitles;
		} catch (IOException e) {
			throw new MuletutException("Can't find file to search");
		} catch (ParseException e) {
			throw new MuletutException("Parse Exception");
		} finally {
			try {
				searcher.close();
			} catch (IOException e) {
				throw new MuletutException("Exception when clsoing searcher");
			}
		}
	}
}
