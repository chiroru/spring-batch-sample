package jp.co.ctc_g.springbatch_sample.database_reading_partitioning;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper<Product> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Product(
				rs.getLong("id"),
				rs.getString("name"), 
				rs.getString("category")
		);
	}
}
