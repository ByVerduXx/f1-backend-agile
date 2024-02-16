INSERT INTO `f1-agile`.`survey` (`permalink`, `title`, `description`, `limit_date`) VALUES
('https://google.com', 'Encuesta 1', 'Descripción de la encuesta 1', '2024-01-16 23:59:59'),
('https://google.com', 'Encuesta 2', 'Descripción de la encuesta 2', '2024-01-09 23:59:59'),
('https://google.com', 'Encuesta 3', 'Descripción de la encuesta 3', '2024-05-05 23:59:59'),
('https://google.com', 'Encuesta 4', 'Descripción de la encuesta 4', '2024-10-07 23:59:59');

-- Asociaciones para la Encuesta 1
INSERT INTO `f1-agile`.`survey_driver` (`id_survey`, `id_driver`) VALUES
                                                                      (1, 1),
                                                                      (1, 2),
                                                                      (1, 3),
                                                                      (1, 4),
                                                                      (1, 5);

-- Asociaciones para la Encuesta 2
INSERT INTO `f1-agile`.`survey_driver` (`id_survey`, `id_driver`) VALUES
                                                                      (2, 6),
                                                                      (2, 7),
                                                                      (2, 8),
                                                                      (2, 9),
                                                                      (2, 10);

-- Asociaciones para la Encuesta 3
INSERT INTO `f1-agile`.`survey_driver` (`id_survey`, `id_driver`) VALUES
                                                                      (3, 1),
                                                                      (3, 3),
                                                                      (3, 5),
                                                                      (3, 7),
                                                                      (3, 9);

-- Asociaciones para la Encuesta 4
INSERT INTO `f1-agile`.`survey_driver` (`id_survey`, `id_driver`) VALUES
                                                                      (4, 2),
                                                                      (4, 4),
                                                                      (4, 6),
                                                                      (4, 8),
                                                                      (4, 10);

INSERT INTO `f1-agile`.`vote` (`id_survey`, `id_driver`, `voter_name`, `voter_email`) VALUES
-- Votos para el piloto 1
(1, 1, 'Votante A', 'votantea@example.com'),

-- Votos para el piloto 2
(1, 2, 'Votante B', 'votanteb@example.com'),
(1, 2, 'Votante C', 'votantec@example.com'),
(1, 2, 'Votante D', 'votanted@example.com'),
(1, 2, 'Votante E', 'votantee@example.com'),
(1, 2, 'Votante F', 'votantef@example.com'),

-- Votos para el piloto 3
(1, 3, 'Votante G', 'votanteg@example.com'),
(1, 3, 'Votante H', 'votanteh@example.com'),

-- Votos para el piloto 4
(1, 4, 'Votante I', 'votantei@example.com'),
(1, 4, 'Votante J', 'votantej@example.com'),
(1, 4, 'Votante K', 'votantek@example.com'),
(1, 4, 'Votante L', 'votantel@example.com'),

-- Votos para el piloto 5
(1, 5, 'Votante M', 'votantem@example.com'),
(1, 5, 'Votante N', 'votanten@example.com'),
(1, 5, 'Votante O', 'votanteo@example.com');




