package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.domain.TestEntry;

import java.util.List;

public interface QuestionsDao {

    List<TestEntry> getEntries();

}
