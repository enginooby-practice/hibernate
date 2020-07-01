# List of practiced topics
- Configure hibernate xml file (hibernate.cfg.xml)
- CRUD features
- SessionFactory and Session
- Start and commit a Transaction
- Add no-arg constructor for entity class.
- @Entity [javax.persistence]
- @Id
- @GeneratedValue
  - GenerationType.IDENTITY
- @Table: map to table in the database [javax.persistence]
- @Column: map to column of the table
- @OneToOne: uni-directional (use @JoinColumn) and bi-directional (use 'mappedBy')
  - 'mappedBy' attribute: used in the class entity which does not contain the foreign key.
  -  @JoinColum: used in the table class entity containing the foreign key.
- @OneToMany
  - Uni-directional: @JoinColumn used in the class entity which does not contain the foreign key.
- Cascade types: CascadeType.ALL, CascadeType.PERSIST (save), CascadeType.REMOVE
- Eager loading and Lazy loading; Fetch types: Fetch.LAZY, Fetch.EAGER
- Handle connection leak issue: catch error and close session.

## Notes and Tips
- Add convenience methods for bi-directional one-to-many relationship
- Use persist with CascadeType.ALL/PERSIST to save all associated objects in one-to-many relationship.
- Prefer Lazy loading then Eager loading.
- To retrieve lazy data, need to open a Hibernate session (connect to the database).
- Resolve Lazy loading issue (because of loading after closing session)
  - Option 1: Call getter method while session is still open [EagerLazyDemo]
  - Option 2: Use Hibernate query with HQL [hibernate.query.Query][FetchJoinDemo]

## !
- (?) Questionnable
- [] Import, Class