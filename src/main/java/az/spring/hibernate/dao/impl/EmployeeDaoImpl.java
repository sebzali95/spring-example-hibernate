package az.spring.hibernate.dao.impl;

import az.spring.hibernate.dao.EmployeeDao;
import az.spring.hibernate.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public class EmployeeDaoImpl extends AbstractSessionFactory implements EmployeeDao {

    @Transactional
    @Override
    public void save(Employee employee) {
        getSession().save(employee);
    }

    @Override
    public void update(Employee employee) {
        getSession().update(employee);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Employee employee = findById(id).orElseThrow(RuntimeException::new);
        getSession().delete(employee);
    }

    @Transactional
    @Override
    public Optional<Employee> findById(Long id) {
        Employee employee = getSession().get(Employee.class, id);
        return Optional.ofNullable(employee);
    }

    @Transactional
    @Override
    public List<Employee> findAll() {
        return getSession().createQuery("select e from Employee e").list();
    }

    @Transactional
    @Override
    public List<Employee> findByName(String name) {
        return getSession().createQuery("select e from Employee e where e.name=:name")
                .setParameter("name", name).list();
    }
}
