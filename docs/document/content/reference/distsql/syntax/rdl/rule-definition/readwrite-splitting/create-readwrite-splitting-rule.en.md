+++
title = "CREATE READWRITE_SPLITTING RULE"
weight = 2
+++

## Description

The `CREATE READWRITE_SPLITTING RULE` syntax is used to create a readwrite splitting rule.

### Syntax

```sql
CreateReadwriteSplittingRule ::=
  'CREATE' 'READWRITE_SPLITTING' 'RULE' readwriteSplittingDefinition (',' readwriteSplittingDefinition)*

readwriteSplittingDefinition ::=
  ruleName '(' (staticReadwriteSplittingDefinition | dynamicReadwriteSplittingDefinition) (',' loadBalancerDefinition)? ')'

staticReadwriteSplittingDefinition ::=
    'WRITE_STORAGE_UNIT' '=' writeStorageUnitName ',' 'READ_STORAGE_UNITS' '(' storageUnitName (',' storageUnitName)* ')'

dynamicReadwriteSplittingDefinition ::=
    'AUTO_AWARE_RESOURCE' '=' resourceName (',' 'WRITE_DATA_SOURCE_QUERY_ENABLED' '=' ('TRUE' | 'FALSE'))?

loadBalancerDefinition ::=
    'TYPE' '(' 'NAME' '=' loadBalancerType (',' propertiesDefinition)? ')'

ruleName ::=
  identifier

writeStorageUnitName ::=
  identifier

storageUnitName ::=
  identifier

resourceName ::=
  identifier
    
loadBalancerType ::=
  string

propertiesDefinition ::=
  'PROPERTIES' '(' key '=' value (',' key '=' value)* ')'

key ::=
  string

value ::=
  literal
```

### Supplement

- Support the creation of static readwrite-splitting rules and dynamic readwrite-splitting rules;
- Dynamic readwrite-splitting rules rely on database discovery rules;
- `loadBalancerType` specifies the load balancing algorithm type, please refer to Load Balance Algorithm;
- Duplicate `ruleName` will not be created.

### Example

#### Create a statics readwrite splitting rule

```sql
CREATE READWRITE_SPLITTING RULE ms_group_0 (
    WRITE_STORAGE_UNIT=write_ds,
    READ_STORAGE_UNITS(read_ds_0,read_ds_1),
    TYPE(NAME="random")
);
```

#### Create a dynamic readwrite splitting rule

```sql
CREATE READWRITE_SPLITTING RULE ms_group_1 (
    AUTO_AWARE_RESOURCE=group_0,
    WRITE_DATA_SOURCE_QUERY_ENABLED=false,
    TYPE(NAME="random",PROPERTIES("read_weight"="2:1"))
);
```

### Reserved word

`CREATE`, `READWRITE_SPLITTING`, `RULE`, `WRITE_RESOURCE`, `READ_RESOURCES`, `AUTO_AWARE_RESOURCE`
, `WRITE_DATA_SOURCE_QUERY_ENABLED`, `TYPE`, `NAME`, `PROPERTIES`, `TRUE`, `FALSE`

### Related links

- [Reserved word](/en/reference/distsql/syntax/reserved-word/)
