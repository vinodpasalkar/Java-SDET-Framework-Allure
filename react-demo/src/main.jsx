import React, { useState } from 'react'; // Imports React and its state hook.
import { createRoot } from 'react-dom/client'; // Imports the React DOM mount function.
import './styles.css'; // Loads the stylesheet.

function App() { // Defines the root component.
  const [user, setUser] = useState(''); // Stores username input state.
  const [pass, setPass] = useState(''); // Stores password input state.
  const [message, setMessage] = useState(''); // Stores form feedback.
  function submit(event) { // Handles login form submission.
    event.preventDefault(); // Prevents a full page reload.
    setMessage(user === 'demo' && pass === 'Password123!' ? 'Login successful' : 'Invalid username or password'); // Checks sample credentials and updates feedback.
  } // Ends submit handler.
  return ( // Begins the returned React markup.
    <main className="panel"> {/* Main content panel. */}
      <h1>Demo application</h1> {/* Displays the app title. */}
      <form onSubmit={submit}> {/* Connects form submit to the handler. */}
        <label htmlFor="username">Username</label> {/* Accessible label for username. */}
        <input id="username" value={user} onChange={e => setUser(e.target.value)} /> {/* Controlled username field. */}
        <label htmlFor="password">Password</label> {/* Accessible label for password. */}
        <input id="password" type="password" value={pass} onChange={e => setPass(e.target.value)} /> {/* Controlled password field. */}
        <button type="submit">Sign in</button> {/* Submits the form. */}
      </form> {/* Ends the form. */}
      <p role="status" aria-live="polite">{message}</p> {/* Announces status updates accessibly. */}
    </main> // Ends main content.
  ); // Ends JSX return.
} // Ends App.

createRoot(document.getElementById('root')).render(<App />); // Mounts React into the root DOM element.
