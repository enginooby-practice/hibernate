# List of practiced topics
- Configure hibernate xml file (hibernate.cfg.xml)
- CRUD features
- @Entity
- @Table: map to table in the database
- @Column: map to column of the table
- @OneToOne: uni-directional (use @JoinColumn) and bi-directional (use 'mappedBy')
- Cascade types: CascadeType.ALL, CascadeType.PERSIST (save), CascadeType.REMOVE
- Handle connection leak issue: catch error and close session.
