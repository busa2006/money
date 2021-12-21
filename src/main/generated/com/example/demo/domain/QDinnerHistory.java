package com.example.demo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDinnerHistory is a Querydsl query type for DinnerHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDinnerHistory extends EntityPathBase<DinnerHistory> {

    private static final long serialVersionUID = -960156046L;

    public static final QDinnerHistory dinnerHistory = new QDinnerHistory("dinnerHistory");

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> insert_dt = createDateTime("insert_dt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath shop = createString("shop");

    public QDinnerHistory(String variable) {
        super(DinnerHistory.class, forVariable(variable));
    }

    public QDinnerHistory(Path<? extends DinnerHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDinnerHistory(PathMetadata metadata) {
        super(DinnerHistory.class, metadata);
    }

}

