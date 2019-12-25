--LUONTILAUSE HENKILÖTAULUN LUONTIA VARTEN

CREATE TABLE henkilo(
id int auto_increment not null,
nimi varchar(30),
osoite varchar(50),
puhnro varchar(15),
primary key(id)
)engine=innoDB;