drop table if exists containers;
create table containers
(
    containerId varchar(150),
    containerName varchar (150),
    containerStatus varchar (150),
    containerPorts varchar (150),
    containerImage varchar (150)
)