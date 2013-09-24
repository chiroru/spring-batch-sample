package jp.co.ctc_g.springbatch_sample.chunk_processing;

import org.springframework.batch.item.ItemProcessor;

public class StringItemProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String item) throws Exception {
        System.out.println("ItemProcessor#process(String)");
        return "*** "+item+" ***";
    }

}
