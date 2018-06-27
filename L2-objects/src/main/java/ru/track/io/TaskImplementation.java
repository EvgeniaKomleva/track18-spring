package ru.track.io;

import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.track.io.vendor.Bootstrapper;
import ru.track.io.vendor.FileEncoder;
import ru.track.io.vendor.ReferenceTaskImplementation;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import java.io.*;
import java.util.Base64;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;

public final class TaskImplementation implements FileEncoder {

    private static class MyBase64 {
        private static class MyOutputStream extends FilterOutputStream {

            private byte[] rest = new byte[2];
            private int restN = 0;

            private OutputStream out;

            public MyOutputStream(OutputStream out) {
                super(out);
                this.out = out;
                rest[0] = rest[1] = 0;
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                if(restN > 0) {
                    int in = 0;
                    if(restN == 2) {
                        in = ((int)(rest[0])) << 16 | (((int)(rest[1])) & 0xff) << 8 | (b[off++] & 0xff);
                    } else {
                        in = ((int)(rest[0])) << 16 | (b[off++] & 0xff) << 8 | (b[off++] & 0xff);
                    }
                    out.write(toBase64[(in >>> 18) & 0x3f]);
                    out.write(toBase64[(in >>> 12) & 0x3f]);
                    out.write(toBase64[(in >>> 6) & 0x3f]);
                    out.write(toBase64[in & 0x3f]);
                    len -= (3 - restN);
                    rest[0] = rest[1] = 0;
                }
                int nLines = len / 3;
                restN = len % 3;
                while (nLines-- > 0) {
                    int in = (b[off++] & 0xff) << 16 | (b[off++] & 0xff) << 8 | (b[off++] & 0xff);
                    out.write(toBase64[(in >>> 18) & 0x3f]);
                    out.write(toBase64[(in >>> 12) & 0x3f]);
                    out.write(toBase64[(in >>> 6) & 0x3f]);
                    out.write(toBase64[in & 0x3f]);
                }
                if(restN > 0) {
                    rest[0] = b[off++];
                    if(restN > 1) rest[1] = b[off++];
                }
            }

            @Override
            public void close() throws IOException {
                if(restN > 0) {
                    int in = 0;
                    if(restN == 2) {
                        in = ((int)(rest[0])) << 16 | (((int)(rest[1])) & 0xff) << 8;
                    } else {
                        in = ((int)(rest[0])) << 16;
                    }
                    if(restN == 2) {
                        out.write(toBase64[(in >>> 18) & 0x3f]);
                        out.write(toBase64[(in >>> 12) & 0x3f]);
                        out.write(toBase64[(in >>> 12) & 0x3f]);
                    } else {
                        out.write(toBase64[(in >>> 18) & 0x3f]);
                        out.write(toBase64[(in >>> 12) & 0x3f]);
                    }
                    for(int i = 0; i < 3 - restN; i++) {
                        out.write('=');
                    }
                    rest[0] = rest[1] = 0;
                }
            }
        }

        private static class Encoder {
            public OutputStream wrap(FileOutputStream out) {
                return new MyOutputStream(out);
            }
        }

        public static Encoder getEncoder () {
            Encoder encoder = new Encoder();
            return encoder;
        }
    }

    /**
     * @param finPath  where to read binary data from
     * @param foutPath where to write encoded data. if null, please create and use temporary file.
     * @return file to read encoded data from
     * @throws IOException is case of input/output errors
     */
    @NotNull
    public File encodeFile(@NotNull String finPath, @Nullable String foutPath) throws IOException {
        /* XXX: https://docs.oracle.com/javase/8/docs/api/java/io/File.html#deleteOnExit-- */
        final File fin = new File(finPath);
        final File fout;

        if (foutPath != null) {
            fout = new File(foutPath);
        } else {
            fout = File.createTempFile("based_file_", ".txt");
            fout.deleteOnExit();
        }

        final MyBase64.Encoder encoder = MyBase64.getEncoder();
        try (
                final FileInputStream fis = new FileInputStream(fin);
                final OutputStream os = encoder.wrap(new FileOutputStream(fout));
        ) {
            int bytesCopied = IOUtils.copy(fis, os); // result unused
        }

        return fout;
    }

    private static final char[] toBase64 = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    public static void main(String[] args) throws IOException {
        final FileEncoder encoder = new TaskImplementation();
        // NOTE: open http://localhost:9000/ in your web browser
        String[] args2 = new String[1];
        args2[0] = "C:\\Users\\ssaa7\\Desktop\\Java\\track17-autumn-master\\track17-autumn-master\\L2-objects\\src\\image_256.png";
        new Bootstrapper(args2, encoder).bootstrap(9000);
    }

}
