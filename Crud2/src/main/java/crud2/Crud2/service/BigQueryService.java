package crud2.Crud2.service;

import com.google.cloud.bigquery.*;
import org.springframework.stereotype.Service;

@Service
public class BigQueryService {

    public void createTable(String datasetName, String tableName) {
        BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();

        TableId tableId = TableId.of(datasetName, tableName);

        // Correct schema based on CSV and UDF
        Field[] fields = new Field[]{
                Field.of("location", StandardSQLTypeName.STRING),
                Field.of("name", StandardSQLTypeName.STRING),
                Field.of("age", StandardSQLTypeName.STRING),
                Field.of("color", StandardSQLTypeName.STRING),
                Field.of("coffee", StandardSQLTypeName.STRING)
        };
        Schema schema = Schema.of(fields);

        StandardTableDefinition tableDefinition = StandardTableDefinition.of(schema);
        TableInfo tableInfo = TableInfo.newBuilder(tableId, tableDefinition).build();

        // Only create the table if it doesn't exist
        if (bigquery.getTable(tableId) == null) {
            bigquery.create(tableInfo);
        }
    }
}
