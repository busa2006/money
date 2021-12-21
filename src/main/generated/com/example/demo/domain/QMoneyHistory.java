package com.example.demo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMoneyHistory is a Querydsl query type for MoneyHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMoneyHistory extends EntityPathBase<MoneyHistory> {

    private static final long serialVersionUID = -667759868L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMoneyHistory moneyHistory = new QMoneyHistory("moneyHistory");

    public final com.example.demo.common.QBaseTimeEntity _super = new com.example.demo.common.QBaseTimeEntity(this);

    public final EnumPath<Action> action = createEnum("action", Action.class);

    //inherited
    public final DateTimePath<java.util.Date> createdDt = _super.createdDt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifiedDt = _super.modifiedDt;

    public final QMoney money;

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final DateTimePath<java.util.Date> transactionDt = createDateTime("transactionDt", java.util.Date.class);

    public QMoneyHistory(String variable) {
        this(MoneyHistory.class, forVariable(variable), INITS);
    }

    public QMoneyHistory(Path<? extends MoneyHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMoneyHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMoneyHistory(PathMetadata metadata, PathInits inits) {
        this(MoneyHistory.class, metadata, inits);
    }

    public QMoneyHistory(Class<? extends MoneyHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.money = inits.isInitialized("money") ? new QMoney(forProperty("money"), inits.get("money")) : null;
    }

}

