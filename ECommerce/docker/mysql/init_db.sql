
CREATE TABLE `clientes` (
  `id` varchar(255) NOT NULL,
  `data_cadastro` date DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `produtos` (
  `id` varchar(255) NOT NULL,
  `data_cadastro` date DEFAULT NULL,
  `disponivel` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `pedidos` (
  `id` varchar(255) NOT NULL,
  `data_cadastro` datetime(6) DEFAULT NULL,
  `status_entrega` varchar(255) DEFAULT NULL,
  `cleinte_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoom51jxlv46u01ugtxyo0enij` (`cleinte_id`),
  CONSTRAINT `FKoom51jxlv46u01ugtxyo0enij` FOREIGN KEY (`cleinte_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ecommecer.produto_pedido definition

CREATE TABLE `produto_pedido` (
  `id` varchar(255) NOT NULL,
  `quantidade` int DEFAULT NULL,
  `produto_id` varchar(255) DEFAULT NULL,
  `pedido_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8kkew2u78bwq2d6cwbll9hx0g` (`produto_id`),
  KEY `FK3bs4r4l15xg41kh4fwj5nwoqb` (`pedido_id`),
  CONSTRAINT `FK3bs4r4l15xg41kh4fwj5nwoqb` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`),
  CONSTRAINT `FK8kkew2u78bwq2d6cwbll9hx0g` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO produtos
( id, nome, valor , data_cadastro, disponivel )
VALUES( UUID(), 'caixa de som', 2100.90, '2020-10-15', 1);

INSERT INTO produtos
( id, nome, valor , data_cadastro, disponivel )
VALUES( UUID(), 'Bateria', 50000, '2020-10-15', 1);

INSERT INTO produtos
( id, nome, valor , data_cadastro, disponivel )
VALUES( UUID(), 'Bateria', 8000, '2020-10-15', 1);

