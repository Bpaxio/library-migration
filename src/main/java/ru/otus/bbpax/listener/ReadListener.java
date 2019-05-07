package ru.otus.bbpax.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

@Slf4j
@AllArgsConstructor
public class ReadListener<T> implements ItemReadListener<T> {
    private final String targetName;

    @Override
    public void beforeRead() { log.info("Начало чтения [{}]", targetName); }

    @Override
    public void afterRead(T o) { log.info("Конец чтения [{}]: {}", targetName, o); }

    @Override
    public void onReadError(Exception e) { log.info("Ошибка чтения [{}]: {}", targetName, e); }
}
