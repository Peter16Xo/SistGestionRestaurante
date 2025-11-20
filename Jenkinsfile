pipeline {
    agent any
    
    // Definimos la herramienta 'Ant' que configuramos en Jenkins
    tools {
        ant 'Ant' 
    }

    stages {
        // Etapa de limpieza: Borra compilaciones anteriores para empezar de cero
        stage('Limpieza Inicial') {
            steps {
                echo 'Limpiando entorno...'
                script {
                    if (isUnix()) {
                        sh 'ant clean'
                    } else {
                        bat 'ant clean'
                    }
                }
            }
        }
        
        // ETAPA 1: BUILD (Compilación del código fuente)
        stage('Stage 1: Build (Compilación)') {
            steps {
                echo 'Compilando código fuente...'
                script {
                    if (isUnix()) {
                        sh 'ant jar'
                    } else {
                        bat 'ant jar'
                    }
                }
            }
        }

        // ETAPA 2: TEST (Ejecución de pruebas unitarias JUnit)
        stage('Stage 2: Test (Pruebas)') {
            steps {
                echo 'Ejecutando pruebas unitarias...'
                script {
                    if (isUnix()) {
                        sh 'ant test'
                    } else {
                        bat 'ant test'
                    }
                }
            }
        }

        // ETAPA 3: DEPLOY (Simulación de despliegue)
        stage('Stage 3: Deploy (Despliegue)') {
            steps {
                echo 'Desplegando aplicación a entorno de Producción...'
                script {
                    // Simulamos el despliegue creando una carpeta de "Release"
                    // y moviendo el JAR final ahí.
                    if (isUnix()) {
                        sh 'mkdir -p deploy'
                        sh 'cp dist/SistemaGestionRestaurante.jar deploy/AppRestaurante_v1.0.jar'
                        echo '¡Despliegue exitoso! Archivo disponible en carpeta /deploy'
                    } else {
                        // Comandos para Windows (si alguien lo corre en local)
                        bat 'mkdir deploy'
                        bat 'copy dist\\SistemaGestionRestaurante.jar deploy\\AppRestaurante_v1.0.jar'
                    }
                }
            }
        }
    }

    // SECCIÓN DE NOTIFICACIONES (Se ejecuta al final de todo)
    post {
        success {
            echo '✅ NOTIFICACIÓN: El Pipeline finalizó con ÉXITO.'
            echo 'El proyecto se compiló, testeó y desplegó correctamente.'
            echo 'Listo para ser revisado.'
        }
        failure {
            echo '❌ NOTIFICACIÓN: El Pipeline FALLÓ.'
            echo 'Revisar los logs para ver qué etapa se rompió.'
        }
        always {
            echo '🏁 Fin de la ejecución del Pipeline.'
        }
    }
}