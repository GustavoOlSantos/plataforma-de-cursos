import ProtectedRoute from "./ProtectedRoute";
import Perfil from "../../features/perfil/";
import VerCurso from "../../features/cursos/verCursoPage/";

export const protectedRoutes = [
  {
    path: "/perfil",
    element: (
      <ProtectedRoute>
        <Perfil />
      </ProtectedRoute>
    ),

    path: "/ver-curso/:slug",
    element: (
      <ProtectedRoute>
        <VerCurso />
      </ProtectedRoute>
    )
  }
];