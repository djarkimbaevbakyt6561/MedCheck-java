package dao;

public interface GeneralDao<T> {
    String add(Long hospitalId, T t);

    String removeById(Long id);

    String updateById(Long id, T t);
}
