export enum ViolationType {
  SPEEDING = 1,
  RED_LIGHT,
  PARKING,
  SEATBELT,
  DOCUMENTS,
  OTHER
}

export enum PaymentStatus {
  UNPAID = 1,
  PAID,
  PARTIAL,
  CANCELLED,
  PENDING
}

export function getViolationTypeDisplay(type: ViolationType): string {
  switch (type) {
    case ViolationType.SPEEDING: return 'Speeding';
    case ViolationType.RED_LIGHT: return 'Running red light';
    case ViolationType.PARKING: return 'Illegal parking';
    case ViolationType.SEATBELT: return 'No seatbelt';
    case ViolationType.DOCUMENTS: return 'Invalid documents';
    case ViolationType.OTHER: return 'Other';
    default: return '';
  }
}

export function getPaymentStatusDisplay(status: PaymentStatus): string {
  switch (status) {
    case PaymentStatus.UNPAID: return 'Unpaid';
    case PaymentStatus.PAID: return 'Paid';
    case PaymentStatus.PARTIAL: return 'Partial';
    case PaymentStatus.CANCELLED: return 'Cancelled';
    case PaymentStatus.PENDING: return 'Pending';
    default: return '';
  }
}
