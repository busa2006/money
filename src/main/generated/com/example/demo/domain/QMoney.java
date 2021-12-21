package com.example.demo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMoney is a Querydsl query type for Money
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMoney extends EntityPathBase<Money> {

    private static final long serialVersionUID = 2000810864L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMoney money = new QMoney("money");

    public final com.example.demo.common.QBaseTimeEntity _super = new com.example.demo.common.QBaseTimeEntity(this);

    public final QCategory category;

    //inherited
    public final DateTimePath<java.util.Date> createdDt = _super.createdDt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifiedDt = _super.modifiedDt;

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final QUser user;

    public QMoney(String variable) {
        this(Money.class, forVariable(variable), INITS);
    }

    public QMoney(Path<? extends Money> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMoney(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMoney(PathMetadata metadata, PathInits inits) {
        this(Money.class, metadata, inits);
    }

    public QMoney(Class<? extends Money> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

