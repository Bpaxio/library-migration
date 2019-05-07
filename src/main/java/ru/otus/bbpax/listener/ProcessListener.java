package ru.otus.bbpax.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;

@Slf4j
@AllArgsConstructor
public class ProcessListener<T, S> implements ItemProcessListener<T, S> {
    private final String targetName;

    @Override
    public void beforeProcess(T item) {
        log.info("Начало обработки [{}]: {}", targetName, item);
    }

    @Override
    public void afterProcess(T item, S result) {
        log.info("Конец обработки [{}]: {} => результат: {}", targetName, item, result);
    }

    @Override
    public void onProcessError(T item, Exception e) {
        log.info("Ошибка обработки [{}]: {} - {}", targetName, item, e.getLocalizedMessage());
    }
}
