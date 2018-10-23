import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View} from 'react-native';

export class Proximity extends Component {
  constructor(props) {
    super(props);
    this.state = {
      context: ""
    };
  }

  render() {
    return (
      <View>
        <Text>{this.state.context}</Text>
      </View>
    );
  }
}