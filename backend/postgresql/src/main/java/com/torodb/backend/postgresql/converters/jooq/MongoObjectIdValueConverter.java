/*
 *     This file is part of ToroDB.
 *
 *     ToroDB is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     ToroDB is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with ToroDB. If not, see <http://www.gnu.org/licenses/>.
 *
 *     Copyright (c) 2014, 8Kdata Technology
 *     
 */

package com.torodb.backend.postgresql.converters.jooq;

import java.sql.Types;

import org.jooq.util.postgres.PostgresDataType;

import com.torodb.backend.converters.jooq.DataTypeForKV;
import com.torodb.backend.converters.jooq.KVValueConverter;
import com.torodb.backend.converters.sql.BinarySqlBinding;
import com.torodb.backend.converters.sql.SqlBinding;
import com.torodb.kvdocument.types.KVType;
import com.torodb.kvdocument.types.MongoObjectIdType;
import com.torodb.kvdocument.values.KVMongoObjectId;
import com.torodb.kvdocument.values.heap.ByteArrayKVMongoObjectId;

/**
 *
 */
public class MongoObjectIdValueConverter implements KVValueConverter<byte[], byte[], KVMongoObjectId> {
    private static final long serialVersionUID = 1L;

    public static final DataTypeForKV<KVMongoObjectId> TYPE = DataTypeForKV.from(PostgresDataType.BYTEA, new MongoObjectIdValueConverter(),Types.BINARY);

    @Override
    public KVType getErasuredType() {
        return MongoObjectIdType.INSTANCE;
    }

    @Override
    public KVMongoObjectId from(byte[] databaseObject) {
        return new ByteArrayKVMongoObjectId(databaseObject);
    }

    @Override
    public byte[] to(KVMongoObjectId userObject) {
        return userObject.getArrayValue();
    }

    @Override
    public Class<byte[]> fromType() {
        return byte[].class;
    }

    @Override
    public Class<KVMongoObjectId> toType() {
        return KVMongoObjectId.class;
    }

    @Override
    public SqlBinding<byte[]> getSqlBinding() {
        return BinarySqlBinding.INSTANCE;
    }

}
