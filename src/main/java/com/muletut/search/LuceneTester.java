package com.muletut.search;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;

public class LuceneTester {
	String indexDir = "src\\main\\resources\\indexes\\tutIndexJApp";
	String dataDir = "src\\main\\resources\\data\\tuts";
	Indexer indexer;
	Searcher searcher;
	QueryParser queryParser;

	public static void main(String[] args) {
		LuceneTester tester;
		try {
			tester = new LuceneTester();
			tester.createIndex();
			tester.searchUsingPrefixQuery("flows");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void searchUsingPrefixQuery(String searchQuery) throws IOException, ParseException {
		searcher = new Searcher(indexDir);
		long startTime = System.currentTimeMillis();
		queryParser = new QueryParser(Version.LUCENE_36, LuceneConstants.CONTENTS,
				new StandardAnalyzer(Version.LUCENE_36));
		Query query = queryParser.parse(searchQuery.replaceAll(" ", "") + "*");
		// Query query = queryParser.parse(searchQuery + "*");
		TopDocs hits = searcher.search(query);
		long endTime = System.currentTimeMillis();
		System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime) + "ms");
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			System.out.println("File: " + doc.get(LuceneConstants.FILE_PATH));
		}
		searcher.close();
	}

	private void createIndex() throws IOException {
		System.out.println("wer"+System.getProperty("user.dir"));
		
		System.out.println("zc"+new File(".").getCanonicalPath());
		System.out.println("ASD" + indexDir);
		indexer = new Indexer(indexDir);
		System.out.println(indexer);
		int numIndexed;
		long startTime = System.currentTimeMillis();
		numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
		long endTime = System.currentTimeMillis();
		indexer.close();
		System.out.println(numIndexed + " File indexed, time taken: " + (endTime - startTime) + " ms");
	}

}
