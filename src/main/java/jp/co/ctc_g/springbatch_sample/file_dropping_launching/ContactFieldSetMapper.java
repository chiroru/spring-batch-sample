package jp.co.ctc_g.springbatch_sample.file_dropping_launching;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ContactFieldSetMapper implements FieldSetMapper<Contact> {

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.item.file.mapping.FieldSetMapper#mapFieldSet
     * (org.springframework.batch.item.file.transform.FieldSet)
     */
    @Override
    public final Contact mapFieldSet(final FieldSet fieldSet)
            throws BindException {
        return new Contact(
                fieldSet.readString("firstname"),
                fieldSet.readString("lastname"),
                fieldSet.readDate("birth","yyyy-MM-dd"));
    }
}

