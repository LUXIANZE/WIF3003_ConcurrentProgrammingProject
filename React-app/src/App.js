import React from 'react';
import SockJS from 'sockjs-client';
import logo from './logo.svg';
import './App.css';

const $ = window.$;

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

var Stomp = require('@stomp/stompjs');
var stompClient = null;

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
    $("#connected-message").show();
  }
  else {
    $("#conversation").hide();
    $("#disconnected-message").show();
  }
  $("#greetings").html("");
}

function connect() {
  $(".connection-status").hide();
  var socket = new SockJS('http://localhost:8080/gs-guide-websocket');
  stompClient = Stomp.Stomp.over(socket);
  var connectCallback = function (frame) {
    setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
        showGreeting(JSON.parse(greeting.body));
        });
    }
  var errorCallback = function (error) {
    $("#connection-failure-message").show();
  }
  stompClient.connect({}, connectCallback, errorCallback);
}

function disconnect() {
  $(".connection-status").hide();
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

function sendName() {
  stompClient.send("/app/hello", {}, JSON.stringify({ 'name': $("#name").val() }));
}

function showGreeting(message) {
  $("#greetings").append("<tr><td>" + message.data + "</td></tr>");
}

$(function () {
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $("#connect").click(function () { connect(); });
  $("#disconnect").click(function () { disconnect(); });
  $("#send").click(function () { sendName(); });
  $(".connection-status").hide();
});

export default App;
