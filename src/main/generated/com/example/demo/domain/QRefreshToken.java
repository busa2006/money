package com.example.demo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRefreshToken is a Querydsl query type for RefreshToken
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRefreshToken extends EntityPathBase<RefreshToken> {

    private static final long serialVersionUID = -728592594L;

    public static final QRefreshToken refreshToken = new QRefreshToken("refreshToken");

    public final com.example.demo.common.QBaseTimeEntity _super = new com.example.demo.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createdDt = _super.createdDt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> keyId = createNumber("keyId", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifiedDt = _super.modifiedDt;

    public final StringPath token = createString("token");

    public QRefreshToken(String variable) {
        super(RefreshToken.class, forVariable(variable));
    }

    public QRefreshToken(Path<? extends RefreshToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRefreshToken(PathMetadata metadata) {
        super(RefreshToken.class, metadata);
    }

}

