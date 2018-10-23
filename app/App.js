/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {StatusBar, View} from 'react-native';
import { NativeRouter, Route } from "react-router-native";

import Home from './containers/Home';
import Connect from "./containers/Connect";
import Ranging from './containers/Ranging';

export default class App extends Component {
  render() {
    return (
    <NativeRouter>
        <View style={{ flex: 1 }}>
          <StatusBar backgroundColor="#0D4062" barStyle="light-content" />
          <Route exact path="/" component={Home} />
        <Route path="/connect" component={Connect} />
        </View>
    </NativeRouter>);
  }
}
