CREATE DATABASE  IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `biblioteca`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	5.6.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `audiovideo`
--

DROP TABLE IF EXISTS `audiovideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audiovideo` (
  `idAudioVideo` int(11) NOT NULL,
  `director` varchar(25) DEFAULT NULL,
  `duracion` varchar(8) NOT NULL,
  `medio` varchar(35) DEFAULT NULL,
  `formato` varchar(35) DEFAULT NULL,
  `tipo` varchar(5) DEFAULT NULL COMMENT 'audio o video',
  PRIMARY KEY (`idAudioVideo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='alamcena los datos de los materiales tipo audio y video';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audiovideo`
--

LOCK TABLES `audiovideo` WRITE;
/*!40000 ALTER TABLE `audiovideo` DISABLE KEYS */;
INSERT INTO `audiovideo` VALUES (1,'Ariel Camilo','4:05:08','DVD','Vob','Video'),(2,'Carlos Martinez','2:01:56','DVD','Vob','Video'),(3,'Ismael Rodriguez','0:05:12','cd','mp3','mp3');
/*!40000 ALTER TABLE `audiovideo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autor` (
  `idAutor` int(11) NOT NULL AUTO_INCREMENT,
  `idMaterial` int(25) NOT NULL,
  `nombreAutor` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idAutor`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (1,1,'Jhon Green'),(2,2,'Larson Hosteler'),(5,3,'Roger S. Pressman'),(6,4,'Real Academia Espanola'),(7,5,'Juan Perez'),(8,9,'Georgue Martin'),(9,6,'Jose Gomez'),(10,7,'Francisco'),(11,8,'Jose perez');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bibliotecarios`
--

DROP TABLE IF EXISTS `bibliotecarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bibliotecarios` (
  `idBibliotecarios` int(11) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `privilegios` varchar(200) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  PRIMARY KEY (`idBibliotecarios`,`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='almacena los usuarios del sistema(bibliotecarios)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibliotecarios`
--

LOCK TABLES `bibliotecarios` WRITE;
/*!40000 ALTER TABLE `bibliotecarios` DISABLE KEYS */;
INSERT INTO `bibliotecarios` VALUES (1,'12345','todos','administrador'),(2,'12345','todos','tecnico'),(3,'12345','todos','otro');
/*!40000 ALTER TABLE `bibliotecarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clasificaciontematica`
--

DROP TABLE IF EXISTS `clasificaciontematica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clasificaciontematica` (
  `idClasificacionTematica` int(7) NOT NULL,
  `idGeneralidadTematica` char(2) NOT NULL,
  `nombre` varchar(70) NOT NULL,
  PRIMARY KEY (`idClasificacionTematica`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasificaciontematica`
--

LOCK TABLES `clasificaciontematica` WRITE;
/*!40000 ALTER TABLE `clasificaciontematica` DISABLE KEYS */;
INSERT INTO `clasificaciontematica` VALUES (1,'0','Bibliografias, Catalogos'),(2,'0','Bibliotecas y Documentacion'),(3,'0','Obras de referencia (enciclopediasa, diccionarios, etc)'),(4,'0','Publicaciones periodicas (revistas, anuarios, directorios)'),(5,'0','Periidismo, Prensa, Diarios'),(11,'1','Metafisica'),(12,'1','Ciencias Ocultas'),(13,'1','Sistemas Filosoficos'),(14,'1','Psicologia'),(15,'1','Logica. Epistemologia'),(16,'1','Moral, Etica, Filosofia practica'),(21,'2','La Biblia'),(22,'2','Cristianismo'),(23,'2','Teologia'),(24,'2','Otras religiones'),(31,'3','Demografia, Estadistica, Sociologia'),(32,'3','Politica'),(33,'3','Economia'),(34,'3','Derecho'),(35,'3','Administracion Publica, Gobierno, Asuntos militares'),(36,'3','Servicios Sociales'),(37,'3','Educacion, formacion, Tiempo Libre'),(38,'3','Turismo, Excursiones'),(39,'3','Ethnologia, Folklore, costumbres, vida social'),(41,'4','Lengua Inglesa'),(42,'4','Lengua Francesa'),(51,'5','Matematicas'),(52,'5','Astronomia, Investigacion espacial'),(53,'5','Fisica'),(54,'5','Quimica, Mineralogia'),(55,'5','Ciencias de la Tierra, Geologia, Meteorologia'),(56,'5','Paleontologia, Fosiles'),(57,'5','Biologia'),(58,'5','Botanica'),(59,'5','Zoologia'),(61,'6','Medicina, Salud'),(62,'6','Ingenieria, Tecnologia'),(63,'6','Agricultura, Ganaderia'),(64,'6','Economia domestica, alimentacion, vestidos, Decoracion'),(68,'6','Informatica'),(69,'6','Construccion'),(71,'7','Urbanismo'),(72,'7','Arquitectura aplicada y artesania'),(73,'7','Artes plasticas, Escultura, Ceramica'),(74,'7','Dibujo, Artes'),(75,'7','Pintura'),(76,'7','Artes graficas, Grabado'),(77,'7','Fotografia'),(78,'7','Musica'),(79,'7','Diversiones, Espectaculos, Juegos, Deportes'),(80,'8','Filologia'),(81,'8','Linguistica y Lenguas'),(82,'8','Literatura, Historia de la literatura'),(90,'9','Prehistoria, Arqueologia'),(91,'9','Geografia, Exploracion de tierras y paises. Viajes '),(93,'9','Historia Universal, Historia de la civilizacion'),(94,'9','Historia de la edad media, Moderna y Comtemporanea'),(574,'5','Medio Ambiente, Ecologia'),(656,'6','Industria, Comercios y Oficios Diversos'),(821,'8','Poesia'),(822,'8','Teatro'),(823,'8','Narrativa, prosa'),(824,'8','Ensayo'),(829,'8','Otros generos Literarios'),(912,'9','Atlas, mapas, Globos'),(914,'9','Geografia de Europa'),(915,'9','Geografia de Asia'),(916,'9','Geografia de Africa'),(917,'9','Geografia de America del norte y Central'),(918,'9','Geografia de America del Sur'),(940,'9','Historia de Europa'),(950,'9','Historia de Asia'),(960,'9','Historia de Africa'),(970,'9','Historia de America del Norte y Central'),(980,'9','Historia deAmerica del Sur'),(990,'9','Historia de Australia Oceania y de las Regiones Articas y Antarticas');
/*!40000 ALTER TABLE `clasificaciontematica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `det_devolucion`
--

DROP TABLE IF EXISTS `det_devolucion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `det_devolucion` (
  `idDet_Devolucion` int(11) NOT NULL AUTO_INCREMENT,
  `idDevolucion` int(11) NOT NULL,
  `idMaterial` int(11) NOT NULL,
  `condicionFisica` varchar(45) NOT NULL,
  PRIMARY KEY (`idDet_Devolucion`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COMMENT='detalle de la devolucion';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `det_devolucion`
--

LOCK TABLES `det_devolucion` WRITE;
/*!40000 ALTER TABLE `det_devolucion` DISABLE KEYS */;
INSERT INTO `det_devolucion` VALUES (1,1,29,'Nuevo'),(2,1,25,'Nuevo'),(3,2,13,'Nuevo'),(4,2,20,'Nuevo'),(5,2,12,''),(6,3,27,''),(7,4,3,'Deterirado'),(8,5,11,'Nuevo'),(9,6,20,'nuevo'),(10,7,16,'Nuevo'),(11,7,27,'Nuevo'),(12,10,3,'Nuevo'),(13,11,1,'Nuevo'),(14,12,26,'Nuevo'),(15,12,15,'Nuevo');
/*!40000 ALTER TABLE `det_devolucion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `det_prestamo`
--

DROP TABLE IF EXISTS `det_prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `det_prestamo` (
  `idDet_Prestamo` int(11) NOT NULL AUTO_INCREMENT,
  `idPrestamo` int(11) NOT NULL,
  `idMaterial` int(11) NOT NULL,
  `condicionFisica` varchar(45) NOT NULL,
  PRIMARY KEY (`idDet_Prestamo`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1 COMMENT='detalle del prestamo';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `det_prestamo`
--

LOCK TABLES `det_prestamo` WRITE;
/*!40000 ALTER TABLE `det_prestamo` DISABLE KEYS */;
INSERT INTO `det_prestamo` VALUES (1,1,13,'Nuevo'),(2,1,20,'Nuevo'),(3,1,12,''),(4,2,29,'Nuevo'),(5,2,25,'Nuevo'),(6,2,1,'Nuevo'),(7,3,27,''),(8,4,12,'nuevo'),(9,5,2,'nuevo'),(10,6,18,'Nuevo'),(11,6,20,'nuevo'),(12,6,36,'nuevo'),(13,7,3,'Deterirado'),(14,7,23,'ddd'),(15,8,40,'Nuevo'),(16,8,11,'Nuevo'),(17,8,40,'Nuevo'),(18,9,16,'Nuevo'),(19,9,27,'Nuevo'),(20,9,3,'Nuevo'),(21,11,26,'Nuevo'),(22,11,15,'Nuevo'),(23,11,16,'nuevo');
/*!40000 ALTER TABLE `det_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devolucion`
--

DROP TABLE IF EXISTS `devolucion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devolucion` (
  `idDevolucion` int(11) NOT NULL AUTO_INCREMENT,
  `idPrestamo` int(11) NOT NULL,
  `hora` varchar(45) NOT NULL,
  `fecha` varchar(45) NOT NULL,
  PRIMARY KEY (`idDevolucion`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COMMENT='almacena las devoluciones realizadas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devolucion`
--

LOCK TABLES `devolucion` WRITE;
/*!40000 ALTER TABLE `devolucion` DISABLE KEYS */;
INSERT INTO `devolucion` VALUES (1,2,'05:00 PM','1/11/15'),(2,1,'05:01 PM','1/11/15'),(3,3,'08:52 PM','1/11/15'),(4,7,'05:34 PM','1/11/15'),(5,8,'06:48 PM','1/11/15'),(6,6,'07:07 PM','1/11/15'),(7,9,'07:15 PM','1/11/15'),(8,9,'07:20 PM','1/11/15'),(9,9,'07:26 PM','1/11/15'),(10,9,'07:39 PM','1/11/15'),(11,2,'09:12 PM','1/11/15'),(12,11,'05:15 PM','1/11/15');
/*!40000 ALTER TABLE `devolucion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccion` (
  `idPersona` int(11) NOT NULL,
  `calle` varchar(30) DEFAULT NULL,
  `numeroEdificio` varchar(15) DEFAULT NULL,
  `ciudad` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (1,'duarte','111','san fco'),(2,'rivas','111','san fco'),(3,'Hermanas Mirabal','22','san fco'),(4,'juan bosh','3','cotui'),(5,'duarte','111','nagua'),(6,'Libertad','56','nagua'),(7,'riva','78','bonao'),(8,'Bono','36','villa riva'),(9,'Los Pomos','3','castillo'),(10,'pedro bono','50','rincon hondo'),(11,'Duarte','34','Pimentel'),(12,'Duarte','12','cotui'),(13,'maria de hostos','15','moca'),(14,'juan bosh','21','bani '),(15,'salcedo','17','mao');
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email`
--

DROP TABLE IF EXISTS `email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email` (
  `idPersona` int(11) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='alamcena los email';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email`
--

LOCK TABLES `email` WRITE;
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` VALUES (1,'nelsonfansub@gmail.com'),(2,'zombie-joel@gmail.com');
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleado` (
  `idEmpleado` int(11) NOT NULL,
  `puesto` varchar(20) DEFAULT NULL,
  `areaTrabajo` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='almacena los datos propios de empleado';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (5,'Director','Administracion'),(10,'Secretaria','Recepcion'),(12,'Sub-Director','Administracion');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estudiante` (
  `idEstudiante` int(15) NOT NULL,
  `nivelAcademico` varchar(30) NOT NULL,
  `institucionAcademica` varchar(45) DEFAULT NULL,
  `tanda` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEstudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='alamcena los datos propios del estudiante';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES (1,'Universitario','UASD','Tarde'),(2,'Universitario','UASD','Noche'),(3,'Universtario','UCNE','Noche'),(4,'Universitario','UASD','Tarde'),(5,'Universitario','CURSA','Tarde'),(6,'Universitario','CURSA','Tarde'),(7,'Universitario','CURSA','Tarde'),(8,'Universitario','CURSA','Tarde'),(9,'Universitario','CURSA','Noche'),(10,'Universitario','CURSA','Noche'),(11,'Universitario','curne','Tarde'),(12,'Universitario','CURNE','Manana');
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generalidadestematicas`
--

DROP TABLE IF EXISTS `generalidadestematicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generalidadestematicas` (
  `idGeneralidadesTematicas` int(11) NOT NULL AUTO_INCREMENT,
  `Generalidad` varchar(45) NOT NULL,
  PRIMARY KEY (`idGeneralidadesTematicas`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COMMENT='las 10 generalidades principales de dewey';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generalidadestematicas`
--

LOCK TABLES `generalidadestematicas` WRITE;
/*!40000 ALTER TABLE `generalidadestematicas` DISABLE KEYS */;
INSERT INTO `generalidadestematicas` VALUES (0,'Filosofia, Psicologia'),(1,'Religion, Teologia'),(2,'Ciencias Sociales'),(3,'Lenguas'),(4,'Ciencias exactas y naturales'),(5,'Ciencias aplicadas'),(6,'Arte, Musica, Juegos, Deportes y Espectaculos'),(7,'Linguistica, Literatura'),(8,'Geografia, Biografias, Historia'),(9,'ObrasGenerales');
/*!40000 ALTER TABLE `generalidadestematicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenesotros`
--

DROP TABLE IF EXISTS `imagenesotros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagenesotros` (
  `idImagenesOtros` int(11) NOT NULL,
  `tamanio` varchar(20) DEFAULT NULL,
  `descripcion` varchar(60) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idImagenesOtros`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='alamcenar tipos de materiales tipo imagenes, mapas o otros';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenesotros`
--

LOCK TABLES `imagenesotros` WRITE;
/*!40000 ALTER TABLE `imagenesotros` DISABLE KEYS */;
INSERT INTO `imagenesotros` VALUES (7,'30pg','Imagen del mapa Mundi ','Imagen'),(8,'17pg','Globo ','Otros');
/*!40000 ALTER TABLE `imagenesotros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventario` (
  `idInventario` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `idMaterial` varchar(45) NOT NULL,
  `estado` varchar(60) DEFAULT NULL,
  `ubicacionFisica` varchar(50) DEFAULT NULL,
  `condicionFisica` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idInventario`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1 COMMENT='para almacenar todo el inventario de los materiales';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (000001,'1','Devuelto','82-1-BajJho-L','Nuevo'),(000002,'1','Prestado','82-2-BajJho-L','nuevo'),(000003,'1','Devuelto','82-3-BajJho-L','Nuevo'),(000004,'6','No Disponible',NULL,NULL),(000005,'6','No Disponible',NULL,NULL),(000006,'6','No Disponible',NULL,NULL),(000007,'6','No Disponible',NULL,NULL),(000008,'6','No Disponible',NULL,NULL),(000009,'6','No Disponible',NULL,NULL),(000010,'6','En Estante','970-10-hisJos-a','Rayado'),(000011,'6','Prestado','970-11-hisJos-a','Nuevo'),(000012,'6','Prestado','970-12-hisJos-a','nuevo'),(000013,'6','Devuelto','970-13-hisJos-a','Nuevo'),(000014,'9','En Estante','82-14-CanGeo-L','Faltan paginas'),(000015,'9','Devuelto','82-15-CanGeo-L','Nuevo'),(000016,'4','Prestado','455-16-DicRea-L','nuevo'),(000017,'4','En Estante','455-17-DicRea-L','nuevo'),(000018,'8','Prestado','912-18-GloJos-i','Nuevo'),(000019,'7','En Estante','912-19-AtlFra-i','Nuevo'),(000020,'7','Devuelto','912-20-AtlFra-i','nuevo'),(000021,'5','En Estante','550-21-OriJua-a','Nuevo'),(000022,'5','En Estante','550-22-OriJua-a','Nuevo'),(000023,'5','Prestado','550-23-OriJua-a','ddd'),(000024,'5','En Estante','550-24-OriJua-a','Nuevo'),(000025,'3','Devuelto','620-25-IngRog-L','Nuevo'),(000026,'3','Devuelto','620-26-IngRog-L','Nuevo'),(000027,'3','Devuelto','620-27-IngRog-L','Nuevo'),(000028,'3','En Estante','620-28-IngRog-L','Nuevo'),(000029,'3','Devuelto','620-29-IngRog-L','Nuevo'),(000030,'2','No Disponible',NULL,NULL),(000031,'2','En Estante','51-31-CalLar-L','chapiao\''),(000032,'2','En Estante','51-32-CalLar-L','Nuevo'),(000033,'2','Prestado','51-33-CalLar-L','Nuevo'),(000034,'2','En Estante','51-34-CalLar-L','Nuevo'),(000035,'2','En Estante','51-35-CalLar-L','Nuevo'),(000036,'2','Prestado','51-36-CalLar-L','nuevo'),(000037,'8','No Disponible',NULL,NULL),(000038,'8','En Estante','912-38-GloJos-i','Nuevo'),(000039,'7','No Disponible',NULL,NULL),(000040,'7','Prestado','912-40-AtlFra-i','Nuevo');
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libro` (
  `ISBN` varchar(20) DEFAULT NULL,
  `idLibro` int(11) NOT NULL,
  `edicion` varchar(45) DEFAULT NULL,
  `casaEditora` varchar(45) DEFAULT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `numeroPaginas` int(11) NOT NULL,
  PRIMARY KEY (`idLibro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='almacena los datos de un material tipo libro';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES ('9780147513731',1,'1ra','Editoral Nube de Tinta','Literatura Juvenil',455),('1183633676119',2,'9na','McGrawHill','Matematicas ',988),('137283728372',3,'7va','McGrawHill','Informatica',743),('232523544',4,'11va','Real Academia Espanola','Diccionarios',1378),('665423113131',9,'12va','Fuego de Dragon','Literatura Medival',523);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material` (
  `idMaterial` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `anioPublicacion` varchar(10) DEFAULT NULL,
  `tipoMaterial` varchar(45) NOT NULL,
  `ClasificacionTematica` int(10) NOT NULL,
  PRIMARY KEY (`idMaterial`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COMMENT='alamacena los materiales pertenecientes a la biblioteca';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'Bajo la misma Estrella','2013','Libro',82),(2,'Calculo','1993','Libro',51),(3,'Ingenieria de Software','2011','Libro',620),(4,'Diccionario De la Real Academia Espanola','2014','Libro',455),(5,'Origen del Universo','2003','audioVideo',550),(6,'historia de Republica Dominicana','2007','audioVudio',970),(7,'Atlas','2011','imagenesOtros',912),(8,'Globo Terraqueo','2000','imagenesOtros',912),(9,'Cancion de Hielo y Fuego','1993','Libro',82);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materialsolicitado`
--

DROP TABLE IF EXISTS `materialsolicitado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materialsolicitado` (
  `idmaterialSolicitado` int(11) NOT NULL AUTO_INCREMENT,
  `tipoMaterial` varchar(25) DEFAULT NULL,
  `nombre` varchar(45) NOT NULL,
  `autor` varchar(45) NOT NULL,
  `edicion` varchar(45) NOT NULL,
  `anio` varchar(10) DEFAULT NULL,
  `clasificacionTematica` varchar(45) NOT NULL,
  `cantidadDeSolicitudes` int(11) NOT NULL,
  PRIMARY KEY (`idmaterialSolicitado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='almacena los materiales que seran solicitados';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialsolicitado`
--

LOCK TABLES `materialsolicitado` WRITE;
/*!40000 ALTER TABLE `materialsolicitado` DISABLE KEYS */;
INSERT INTO `materialsolicitado` VALUES (1,'Libro','Don Qujote de la Mancha','Miguel Servantes','8va','1890','550',5),(2,'ImagenesOtros','Mapa Politico Republica Dominicana','Juan Perez','1ra','2014','912',2),(3,'Libro','La Biblia','-','-','-','21',8);
/*!40000 ALTER TABLE `materialsolicitado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `fechaNacimiento` varchar(50) DEFAULT NULL,
  `cedula` varchar(20) DEFAULT NULL COMMENT 'si la persona es menor de edad puede estar null',
  `fechaRegistro` varchar(50) DEFAULT NULL COMMENT 'fecha en la que se registro la persona',
  `tipoUsuario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COMMENT='esta tabla almacena los principales datos de una persona';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Nelson','frias','M','1950 01 20','05900210666','1/11/15','Estudiante'),(2,'nelson Joel','frias','M','08/05/1988','05900210666','13/12/15','Estudiante'),(10,'joel','cordero','M','01/01/2000','05900000000','13/12/15','Estudiante'),(11,'sannys','suarez','F','02/02/2006','05601010101','13/12/15','Estudiante'),(12,'radi','Mer','M','02/02/2014','02100000000','13/12/15','Estudiante');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prestamo` (
  `idPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) NOT NULL COMMENT 'persona responsable del prestamo',
  `bibliotecario` varchar(25) DEFAULT NULL,
  `horaEntrega` varchar(50) DEFAULT NULL COMMENT 'hora que se realizo el prestamo',
  `fechaEntrega` varchar(50) DEFAULT NULL COMMENT 'fecha que se realizo el prestamo',
  `horaPrestamo` varchar(50) DEFAULT NULL,
  `fechaPrestamo` varchar(50) DEFAULT NULL,
  `lecturaSala` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`idPrestamo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COMMENT='almacena los prestamos de libros realizados';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (1,7,'Bibliotecario1','04:53 PM','12/1/2015','04:52 PM','1/11/15','NO'),(2,1,'Bibliotecario1','19:56:59','12/1/2015','04:56 PM','1/11/15','SI'),(3,2,'Bibliotecario1','23:48:52','12/1/2015','08:47 PM','1/11/15','SI'),(4,8,'Bibliotecario1','09:32 PM','12/1/2015','09:32 PM','1/11/15','NO'),(5,12,'Bibliotecario1','1:5:21','12/1/2015','10:05 PM','1/11/15','SI'),(6,8,'Bibliotecario1','05:08 PM','12/1/2015','05:08 PM','1/11/15','SI'),(7,8,'Bibliotecario1','05:16 PM','12/1/2015','05:16 PM','1/11/15','SI');
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profesor` (
  `idProfesor` int(11) NOT NULL,
  `areaAcademica` varchar(45) DEFAULT NULL,
  `institucionAcademica` varchar(45) DEFAULT NULL,
  `tanda` varchar(45) DEFAULT NULL,
  `codigoProfesor` varchar(20) NOT NULL,
  PRIMARY KEY (`idProfesor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='almacena los datos propios del profesor';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (6,'Informatica','UASD','Matutina','707'),(7,'Informatica','UASD','Matutina','110'),(8,'Matematica','UASD','Vespertina','1111');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefonos`
--

DROP TABLE IF EXISTS `telefonos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefonos` (
  `idPersona` int(11) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='almacena los telefonos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefonos`
--

LOCK TABLES `telefonos` WRITE;
/*!40000 ALTER TABLE `telefonos` DISABLE KEYS */;
INSERT INTO `telefonos` VALUES (1,'8492207648');
/*!40000 ALTER TABLE `telefonos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-14 10:46:25
