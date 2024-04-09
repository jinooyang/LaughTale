package com.jshi.laughtale.utils;

import com.jshi.laughtale.common.BaseException;
import com.jshi.laughtale.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Slf4j
public class FileUtils {

    private static final String SAVE_PATH = "/images";

    public static String save(MultipartFile file, String... variable) throws IOException {
        String originalFilename = file.getOriginalFilename();
        variable = Arrays.copyOf(variable, variable.length + 1);
        variable[variable.length - 1] = originalFilename;
        Path path = Path.of(SAVE_PATH, variable);
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        file.transferTo(path);
        return path.toString();
    }

    public static void remove(String filename) throws IOException {
        Path path = Path.of(filename);
        if (!Files.exists(path)) {
            log.error("Image Path : {}", path);
            throw new BaseException(ErrorCode.FILE_NOT_FOUND);
        }
        Files.delete(path);
    }
}
