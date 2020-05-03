package com.codspire.mongodbexample.serializer;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bson.types.ObjectId;

public class ObjectIdSerializerModule extends SimpleModule {
    public ObjectIdSerializerModule() {
        super("ObjectIdSerializerModule");
        addSerializer(ObjectId.class, new ObjectIdSerializer());
    }
}
