package GUI;


import Logic.DAO.*;
import Logic.DTO.*;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ProfessionalPracticesSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n      MENU DE PRUEBAS DAO       \n");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Registrar Estudiante");
            System.out.println("3. Registrar Profesor");
            System.out.println("4. Registrar Coordinador");
            System.out.println("5. Registrar Proyecto");
            System.out.println("6. Registrar Reporte");
            System.out.println("7. Registrar Actividad");
            System.out.println("8. Registrar Reporte Parcial");
            System.out.println("9. Registrar Reporte Mensual");
            System.out.println("10. Registrar Reporte Final");
            System.out.println("11. Listar Estudiantes");
            System.out.println("12. Listar Profesores");
            System.out.println("13. Listar Reportes Mensuales");
            System.out.println("14. Salir");

            System.out.print("\n     Selecciona una opcion:     \n");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1: {
                    UserDAO dao = new UserDAO();
                    User user = new User();

                    System.out.print("Primer nombre: ");
                    user.setFirstName(scanner.nextLine());

                    System.out.print("Segundo nombre: ");
                    user.setMiddleName(scanner.nextLine());

                    System.out.print("Apellido paterno: ");
                    user.setPaternalSurname(scanner.nextLine());

                    System.out.print("Apellido materno: ");
                    user.setMaternalSurname(scanner.nextLine());

                    user.setStatus(true);

                    System.out.print("Genero: ");
                    user.setGender(scanner.nextLine());

                    System.out.print("Correo: ");
                    user.setEmail(scanner.nextLine());

                    System.out.print("Contraseña: ");
                    user.setPassword(scanner.nextLine());

                    System.out.println(dao.registerUser(user));
                    break;
                }

                case 2: {
                    StudentDAO dao = new StudentDAO();
                    Student student = new Student();

                    System.out.print("Matricula: ");
                    student.setEnrollment(scanner.nextLine());

                    System.out.print("Fecha nacimiento (YYYY-MM-DD): ");
                    student.setBirthdate(Date.valueOf(scanner.nextLine()));

                    System.out.print("Horas cubiertas: ");
                    student.setHoursCovered(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Lengua indigena (true/false): ");
                    student.setIndigenousLanguage(scanner.nextBoolean());
                    scanner.nextLine();

                    System.out.print("Sector social: ");
                    student.setSocialSector(scanner.nextLine());

                    System.out.print("ID Usuario: ");
                    student.setIdUser(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("NRC: ");
                    student.setNrc(scanner.nextLine());

                    System.out.println(dao.registerStudent(student));
                    break;
                }

                case 3: {
                    ProfessorDAO dao = new ProfessorDAO();
                    Professor professor = new Professor();

                    System.out.print("Numero personal: ");
                    professor.setNumberStaff(scanner.nextLine());

                    System.out.print("Turno: ");
                    professor.setShift(scanner.nextLine());

                    System.out.print("Fecha registro (YYYY-MM-DD): ");
                    professor.setRegistrationDate(Date.valueOf(scanner.nextLine()));

                    System.out.print("Fecha baja (YYYY-MM-DD): ");
                    professor.setTerminationDate(Date.valueOf(scanner.nextLine()));

                    System.out.print("Tiempo servicio: ");
                    professor.setServiceTime(scanner.nextLine());

                    System.out.print("Es coordinador (true/false): ");
                    professor.setIsCoordinator(scanner.nextBoolean());
                    scanner.nextLine();

                    System.out.print("ID Usuario: ");
                    professor.setIdUser(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println(dao.registerProfessor(professor));
                    break;
                }

                case 4: {
                    CoordinatorDAO dao = new CoordinatorDAO();
                    Coordinator coordinator = new Coordinator();

                    System.out.print("Numero de personal: ");
                    coordinator.setNumberStaff(scanner.nextLine());

                    System.out.print("Fecha de registro (YYYY-MM-DD): ");
                    coordinator.setRegistrationDate(Date.valueOf(scanner.nextLine()));

                    System.out.print("Fecha de baja (YYYY-MM-DD): ");
                    coordinator.setTerminationDate(Date.valueOf(scanner.nextLine()));

                    System.out.print("Tiempo de servicio: ");
                    coordinator.setServiceTime(scanner.nextLine());

                    System.out.print("ID Usuario: ");
                    coordinator.setIdUser(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println(dao.registerCoordinator(coordinator));
                    break;
                }

                case 5: {
                    ProjectDAO dao = new ProjectDAO();
                    Project project = new Project();

                    System.out.print("Nombre proyecto: ");
                    project.setProjectName(scanner.nextLine());

                    System.out.print("Duracion: ");
                    project.setDuration(scanner.nextLine());

                    System.out.print("Descripcion: ");
                    project.setDescription(scanner.nextLine());

                    System.out.print("Cupo: ");
                    project.setAvailableSpaces(scanner.nextInt());
                    scanner.nextLine();

                    project.setStatus(true);

                    System.out.print("Metodologia: ");
                    project.setProjectMethodology(scanner.nextLine());

                    System.out.print("ID Organizacion: ");
                    project.setIdLikedOrganization(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println(dao.registerProject(project));
                    break;
                }

                case 6: {
                    ReportDAO dao = new ReportDAO();
                    Report report = new Report();

                    System.out.print("Fecha entrega (YYYY-MM-DD): ");
                    report.setReportSubmissionDate(Date.valueOf(scanner.nextLine()));

                    System.out.print("Fecha realizacion (YYYY-MM-DD): ");
                    report.setReportCompletionDate(Date.valueOf(scanner.nextLine()));

                    System.out.print("Descripcion: ");
                    report.setDescription(scanner.nextLine());

                    System.out.print("Calificacion: ");
                    report.setQualification(scanner.nextDouble());
                    scanner.nextLine();

                    System.out.print("Comentarios: ");
                    report.setPersonalComments(scanner.nextLine());

                    System.out.print("Matricula: ");
                    report.setEnrollment(scanner.nextLine());

                    System.out.println(dao.registerReport(report));
                    break;
                }

                case 7: {
                    ActivityDAO dao = new ActivityDAO();
                    Activity activity = new Activity();

                    System.out.print("Nombre actividad: ");
                    activity.setActivityName(scanner.nextLine());

                    System.out.print("Descripcion: ");
                    activity.setDescription(scanner.nextLine());

                    System.out.print("Valor: ");
                    activity.setValue(scanner.nextDouble());
                    scanner.nextLine();

                    System.out.print("Fecha entrega (YYYY-MM-DD): ");
                    activity.setDueDateActivity(Date.valueOf(scanner.nextLine()));

                    System.out.print("ID Proyecto: ");
                    activity.setIdActivityProject(scanner.nextInt());

                    System.out.print("ID Reporte: ");
                    activity.setIdActivityReport(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println(dao.addActivity(activity));
                    break;
                }

                case 8: {
                    PartialReportDAO dao = new PartialReportDAO();
                    PartialReport pr = new PartialReport();

                    System.out.print("Archivo: ");
                    pr.setPartialReportFile(scanner.nextLine());

                    System.out.print("Resultados: ");
                    pr.setResultsObtained(scanner.nextLine());

                    System.out.print("ID Reporte: ");
                    pr.setIdReport(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println(dao.registerPartialReport(pr));
                    break;
                }

                case 9: {
                    MonthlyReportDAO dao = new MonthlyReportDAO();
                    MonthlyReport mr = new MonthlyReport();

                    System.out.print("Archivo: ");
                    mr.setMontlyReportFile(scanner.nextLine());

                    System.out.print("Actividades: ");
                    mr.setActivitiesPerformed(scanner.nextLine());

                    System.out.print("ID Reporte: ");
                    mr.setIdReport(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println(dao.registerMonthlyReport(mr));
                    break;
                }

                case 10: {
                    FinalReportDAO dao = new FinalReportDAO();
                    FinalReport fr = new FinalReport();

                    System.out.print("Archivo: ");
                    fr.setFinalReportFile(scanner.nextLine());

                    System.out.print("Resultado: ");
                    fr.setFinalDeliverable(scanner.nextLine());

                    System.out.print("ID Reporte: ");
                    fr.setIdReport(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println(dao.registerFinalReport(fr));
                    break;
                }

                case 11: {
                    StudentDAO dao = new StudentDAO();
                    List<Student> students = dao.getStudents();

                    for (Student s : students) {
                        System.out.println(s.getEnrollment() + " - " + s.getSocialSector());
                    }
                    break;
                }

                case 12: {
                    ProfessorDAO dao = new ProfessorDAO();
                    List<Professor> list = dao.getProfessors();

                    for (Professor p : list) {
                        System.out.println(p.getNumberStaff() + " - " + p.getShift());
                    }
                    break;
                }

                case 13: {
                    MonthlyReportDAO dao = new MonthlyReportDAO();
                    List<MonthlyReport> list = dao.getMonthlyReports();

                    for (MonthlyReport m : list) {
                        System.out.println(m.getReportNumber() + " - " + m.getActivitiesPerformed());
                    }
                    break;
                }

                case 14:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (option != 14);

        scanner.close();
    }

}
