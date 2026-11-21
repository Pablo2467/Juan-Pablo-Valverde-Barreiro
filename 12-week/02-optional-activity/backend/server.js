const express = require("express");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");

const app = express();
app.use(express.json());

// En producción esto va en una variable de entorno (.env), nunca en el código
const JWT_SECRET =
  process.env.JWT_SECRET || "cambia-este-secreto-en-produccion";
const JWT_EXPIRES_IN = "1h";

// "Base de datos" en memoria (para la práctica). Contraseña: 1234
const users = [
  {
    id: 1,
    username: "admin",
    passwordHash: bcrypt.hashSync("1234", 10),
    role: "admin",
  },
];

// ---------- Middleware de autenticación ----------
function authRequired(req, res, next) {
  const header = req.headers.authorization || "";
  const [scheme, token] = header.split(" ");

  if (scheme !== "Bearer" || !token) {
    return res
      .status(401)
      .json({ error: "Token requerido (Authorization: Bearer <token>)" });
  }
  try {
    req.user = jwt.verify(token, JWT_SECRET); // lanza error si es inválido o expiró
    next();
  } catch (err) {
    return res.status(401).json({ error: "Token inválido o expirado" });
  }
}

// ---------- Rutas públicas ----------
app.get("/", (req, res) => res.json({ message: "API pública" }));

app.post("/login", (req, res) => {
  const { username, password } = req.body || {};
  const user = users.find((u) => u.username === username);

  if (!user || !bcrypt.compareSync(password || "", user.passwordHash)) {
    return res.status(401).json({ error: "Credenciales inválidas" });
  }
  const token = jwt.sign(
    { sub: user.id, username: user.username, role: user.role },
    JWT_SECRET,
    { expiresIn: JWT_EXPIRES_IN },
  );
  res.json({ token, tokenType: "Bearer", expiresIn: JWT_EXPIRES_IN });
});

// ---------- Rutas protegidas ----------
app.get("/perfil", authRequired, (req, res) => {
  res.json({
    id: req.user.sub,
    username: req.user.username,
    role: req.user.role,
  });
});

app.get("/productos", authRequired, (req, res) => {
  res.json([
    { id: 1, nombre: "Teclado" },
    { id: 2, nombre: "Mouse" },
  ]);
});

const PORT = process.env.PORT || 3000;
if (require.main === module) {
  app.listen(PORT, () => console.log(`API en http://localhost:${PORT}`));
}
module.exports = app;
