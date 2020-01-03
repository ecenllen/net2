package com.yydd.net.net.util.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Shanlin on 2017/4/5.
 */

public class BooleanTypeAdapter extends TypeAdapter<Boolean> {
    @Override
    public Boolean read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        } else if (in.peek() == JsonToken.STRING) {
            // support strings for compatibility with GSON 1.7
            String next = in.nextString();
            return next != null && ("true".equalsIgnoreCase(next) || "1".equals(next));
        }
        return in.nextBoolean();
    }

    @Override
    public void write(JsonWriter out, Boolean value) throws IOException {
        out.value(value);
    }
}
