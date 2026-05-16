import ProtectedRoute from "./ProtectedRoute";
import Perfil from "../../features/perfil/";
import VerCurso from "../../features/cursos/verCursoPage/";
import Certificado from "../../features/cursos/certificadoPage/";

export const protectedRoutes = [
  {
    path: "/perfil",
    element: (
      <ProtectedRoute>
        <Perfil />
      </ProtectedRoute>
    ),
  },
  {
    path: "/ver-curso/:slug",
    element: (
      <ProtectedRoute>
        <VerCurso />
      </ProtectedRoute>
    ),
  },
  { 
    path: "/cursos/:slug/certificado",
    element: (
      <ProtectedRoute>
        <Certificado />
      </ProtectedRoute>
    )
  }
];