import {
  cloneDeep,
  isEmpty,
  transform,
  isObject,
  isEqual,
  filter,
  differenceBy,
  groupBy,
  mapValues
} from "lodash";
import * as moment from 'moment';

export class LodashUtils {

  static deepCopy(value: any) {
    return cloneDeep(value);
  }

  static isEmptyObject(object: any): boolean {
    return isEmpty(object);
  }

  static filter(dataArray: any[], citeria: any, property: string) {
    return filter(dataArray, (da: any) => {
      return da[property] == citeria;
    });
  }

  static differenceBy(data: any[], data2: any[], property: any) {
    return differenceBy(data, data2, property);
  }

  static groupBy(data: any[], property: any) {
    return mapValues(groupBy(data, property));
  }

}

export class MomentUtils {
  static formatMoment(value: string, formatType: string): string {
    return moment(value).format(formatType);
  }

  static difference(value: string, diffType: any): number {
    return moment().diff(value, diffType);
  }

  static setDatePrimaryCard() {
    return moment(new Date())
      .subtract(18, "years")
      .format("YYYY-MM-DD");
  }

  static setDateSupplementaryCard() {
    return moment(new Date())
      .subtract(16, "years")
      .format("YYYY-MM-DD");
  }
}
