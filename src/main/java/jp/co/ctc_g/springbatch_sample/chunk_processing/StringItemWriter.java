package jp.co.ctc_g.springbatch_sample.chunk_processing;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class StringItemWriter implements ItemWriter<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringItemWriter.class);

    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("ItemWriter#write(List)");
        for(String item : items) {
            LOGGER.info("writing "+item);
            System.out.println("writing "+item);
        }
    }

}
