package com.stealer.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import org.lwjgl.system.MemoryUtil;

public class Utils {
    public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
        ByteBuffer buffer;
        Path path = Path.of(resource);
        
        if (Files.exists(path)) {
            // Read file into ByteBuffer
            try (InputStream inputStream = Files.newInputStream(path);
                 ReadableByteChannel rbc = Channels.newChannel(inputStream)) {
                buffer = MemoryUtil.memAlloc(bufferSize);
                while (rbc.read(buffer) != -1) ;
            }
        } else {
            throw new IOException("File not found: " + resource);
        }

        buffer.flip(); // Prepare buffer for reading
        return buffer;
    }
    // public static float[] sumMatrices(float[] a, float[] b) {
    //     float[] sum = new float[a.length];
    //     for (int i = 0; i < a.length; i++) {
    //         sum[i] = a[i] + b[i];
    //     }
    //     return sum;
    // }
}
