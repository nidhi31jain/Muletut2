package com.muletut.service;

import java.util.ArrayList;

import org.apache.lucene.search.TopDocs;

import com.muletut.exceptions.MuletutException;

public interface MuletutService {

	/**
	 * Method to add index menu items
	 * 
	 * @return
	 * @throws MuletutException
	 */
	boolean addMenuItems() throws MuletutException;

	/**
	 * Method to add get index menu items
	 * 
	 * @return
	 * @throws MuletutException
	 */
	ArrayList<String> getIndexMenu() throws MuletutException;

	/**
	 * Method to read the tutorial file
	 * 
	 * @param title
	 * @return
	 * @throws MuletutException
	 */
	String readFile(String title) throws MuletutException;

	/**
	 * Method to add reference menu items
	 * 
	 * @return
	 * @throws MuletutException
	 */
	boolean addReferenceMenuItems() throws MuletutException;

	/**
	 * Method to Get reference menu items
	 * 
	 * @return
	 * @throws MuletutException
	 */
	ArrayList<String> getReferenceMenu() throws MuletutException;

	/**
	 * Method to add blog posts titles
	 * 
	 * @return
	 * @throws MuletutException
	 */
	boolean addBlogPosts() throws MuletutException;

	/**
	 * Method to get Blog posts
	 * 
	 * @return
	 * @throws MuletutException
	 */
	ArrayList<String> getBlogPosts() throws MuletutException;

	/**
	 * Method to read blog post
	 * 
	 * @param title
	 * @return
	 * @throws MuletutException
	 */
	String readPost(String title) throws MuletutException;

	/**
	 * @param postName
	 * @return
	 * @throws MuletutException
	 */
	ArrayList<String> getBlogPostsForSingle(String postName) throws MuletutException;

	/**
	 * @throws MuletutException
	 */
	void createIndex() throws MuletutException;

	/**
	 * @param searchString
	 * @return 
	 * @throws MuletutException
	 */
	ArrayList<String> search(String searchString) throws MuletutException;
}
