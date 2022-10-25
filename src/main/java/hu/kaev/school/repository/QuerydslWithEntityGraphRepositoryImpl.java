package hu.kaev.school.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import hu.kaev.school.model.Course;

public class QuerydslWithEntityGraphRepositoryImpl extends SimpleJpaRepository<Course, Long> implements QuerydslWithEntityGraphRepository<Course, Long>{

	private EntityManager em;
	private EntityPath<Course> path;
	private PathBuilder<Course> builder;
	private Querydsl querydsl;
	
	public QuerydslWithEntityGraphRepositoryImpl(EntityManager em) {
		super(Course.class, em);
		this.em = em;
		this.path = SimpleEntityPathResolver.INSTANCE.createPath(Course.class);
		this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
		this.querydsl = new Querydsl(em, builder);
	
	}

	@Override
	public List<Course> findAll(Predicate predicate, String entityGraphName, EntityGraph.EntityGraphType egType) {

		JPAQuery query = querydsl.createQuery(path).where(predicate);
		query.setHint(egType.getKey(), em.getEntityGraph(entityGraphName));
		
		return query.fetch();
		
	}
	
}
