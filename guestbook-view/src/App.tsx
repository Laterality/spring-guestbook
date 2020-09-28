import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import "./configuration/axios-config";

import "./App.css";

import HomePage from "./page/home";

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route>
            <HomePage />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
