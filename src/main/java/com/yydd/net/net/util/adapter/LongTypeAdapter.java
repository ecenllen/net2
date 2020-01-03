package com.yydd.net.net.util.adapter;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Shanlin on 2017/4/5.
 */

public class LongTypeAdapter extends TypeAdapter<Long> {
    @Override
    public void write(JsonWriter out, Long value) throws IOException {
        out.value(value);
    }

    @Override
    public Long read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        } else if (in.peek() == JsonToken.STRING) {

            try {
                return Long.valueOf(in.nextString());
            } catch (NumberFormatException e) {
            }
            return 0L;
        }
        try {
            return in.nextLong();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }
}
