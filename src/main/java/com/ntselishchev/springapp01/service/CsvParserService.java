package com.ntselishchev.springapp01.service;

import java.io.InputStream;
import java.util.List;

public interface CsvParserService {

    List<List<String>> parseCsv(InputStream inputStream);

}
