import Home from "../../features/home/";
import Login from "../../features/auth/login/";
import Cadastro from "../../features/auth/cadastro/";
import NotFound from "../../features/not-found/";

export const publicRoutes = [
  {
    path: "/",
    element: <Home />
  },
  {
    path: "/login",
    element: <Login />
  },
  {
    path: "/cadastro",
    element: <Cadastro />
  },
  {
    path: "*",
    element: <NotFound />
  }
];