import React, { Component } from 'react';
import { StyleSheet, Text, View, Image } from 'react-native';
import { DeviceEventEmitter } from 'react-native';
import Beacons from 'react-native-beacons-manager';

import Header from '../components/UI/Header';
import Button from '../components/UI/Button';

class BeaconsList extends Component {
  state = {
    inRegion: false
  };

  async componentDidMount() {
    this._mounted = true;
    Beacons.detectIBeacons();

    try {
      Beacons.setForegroundScanPeriod(1500);
      await Beacons.startMonitoringForRegion({
        identifier: 'REGION1',
        uuid: 'BC93FB2E-6CFA-4A8E-BDAE-0D2664F9216F'
      });
      console.log(`Connecting to beacon.`);
    } catch (err) {
      console.log(`Could not start connection, error: ${err}`);
    }

    DeviceEventEmitter.addListener('regionDidEnter', data => {
      console.log('Entra', data);
      if (this._mounted) {
        this.setState({ inRegion: true });
      }
    });

    DeviceEventEmitter.addListener('regionDidExit', data => {
      console.log('Sale', data);
      if (this._mounted) {
        this.setState({ inRegion: false });
      }
    });
  }

  async componentWillUnmount() {
    this._mounted = false;
    DeviceEventEmitter.removeListener('regionDidEnter');
    DeviceEventEmitter.removeListener('regionDidExit');
    await Beacons.stopMonitoringForRegion({
      identifier: '53a527dd34f40482f15299d50844df06',
      uuid: 'CBF53FBE-B334-BBA2-F7FB-75DF2D783AAD'
    });
  }

  render() {
    return (
      <View style={styles.container}>
        <Header text="Connecting to Beacons" />
        <Button
          onPress={async () => this.props.history.push('/')}
          text="Stop"
          color="#5c58a7"
        />
        <View style={styles.centerContainer}>
          <Image
            blurRadius={this.state.inRegion ? 0 : 1}
            tintColor={this.state.inRegion ? null : 'rgba(128, 128, 128, 0.5)'}
            source={require('./beacon.png')}
          />
          <Text>
            {this.state.inRegion ? 'Inside Region' : 'Outside Region'}
          </Text>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#F5FCFF'
  },
  centerContainer: {
    justifyContent: 'center',
    alignItems: 'center',
    paddingTop: 30
  },
  empty: {
    fontSize: 20,
    marginTop: 200,
    textAlign: 'center',
    margin: 10
  }
});

export default BeaconsList;
