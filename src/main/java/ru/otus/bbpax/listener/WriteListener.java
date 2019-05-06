package ru.otus.bbpax.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class WriteListener<S> implements ItemWriteListener<S> {
    private final String targetName;

    @Override
    public void beforeWrite(List<? extends S> items) {
        log.info("Начало записи [{}]: {}", targetName, items);
    }

    @Override
    public void afterWrite(List<? extends S> items) {
        log.info("Конец записи [{}]: {}", targetName, items);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends S> items) {
        log.info("Ошибка записи [{}]: {}", targetName, exception.getLocalizedMessage());
    }
}
