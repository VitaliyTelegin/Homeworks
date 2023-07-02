package ru.sbercourse.filmlibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.webjars.NotFoundException;
import ru.sbercourse.filmlibrary.model.GenericModel;
import ru.sbercourse.filmlibrary.repository.GenericRepository;

import java.time.LocalDateTime;
import java.util.List;

public abstract class GenericService<T extends GenericModel> {

  private final GenericRepository<T> repository;

  protected GenericService(GenericRepository<T> repository) {
    this.repository = repository;
  }

  public List<T> listAll() {
    return repository.findAll();
  }

  public Page<T> listAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public T getOne(Long id) {
    return repository.findById(id).orElseThrow(() ->
            new NotFoundException("Запись с переданным id не найдена"));
  }

  public T create(T object) {
    object.setCreatedBy("ADMIN");
    object.setCreatedWhen(LocalDateTime.now());
    return repository.save(object);
  }

  public T update(T object) {
    return repository.save(object);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public void softDelete(Long id) {
    T object = getOne(id);
    object.setDeleted(true);
    object.setDeletedBy("ADMIN");
    object.setDeletedWhen(LocalDateTime.now());
    update(object);
  }

  public void restore(Long id) {
    T object = getOne(id);
    object.setDeleted(false);
    object.setDeletedBy(null);
    object.setDeletedWhen(null);
    update(object);
  }

  public boolean existsById(Long id) {
    return repository.existsById(id);
  }
}
