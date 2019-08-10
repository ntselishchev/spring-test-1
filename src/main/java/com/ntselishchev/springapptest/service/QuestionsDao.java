package com.ntselishchev.springapptest.service;

import com.ntselishchev.springapptest.domain.TestEntry;

import java.util.List;

public interface QuestionsDao {

    List<TestEntry> getEntries();

}
